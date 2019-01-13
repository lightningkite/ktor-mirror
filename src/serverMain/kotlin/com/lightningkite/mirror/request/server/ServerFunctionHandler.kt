package com.lightningkite.mirror.request.server

import com.lightningkite.kommon.exception.ExceptionNames
import com.lightningkite.kommon.exception.stackTraceString
import com.lightningkite.kommunicate.mirror.RemoteExceptionData
import com.lightningkite.mirror.info.*
import com.lightningkite.mirror.request.HasPermissions
import com.lightningkite.mirror.request.Request
import com.lightningkite.mirror.request.permissions
import com.lightningkite.mirror.request.permitted
import com.lightningkite.mirror.server.receive
import com.lightningkite.mirror.server.respond
import com.lightningkite.mirror.server.unwrappedPrincipal
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.util.pipeline.ContextDsl
import java.util.*
import kotlin.reflect.KClass

class ServerFunctionHandler<USER>(
        val classInfoRegistry: ClassInfoRegistry
) {

    @ContextDsl
    fun Route.serverFunction(path: String, suppressStackTrace: Boolean = true): Route {
        return post(path) { _ ->
            try {
                val sf = call.receive(Request::class.type)
                val user = call.unwrappedPrincipal<USER>()
                if(!sf.permitted(classInfoRegistry, user as? HasPermissions)) {
                    call.respond(
                            status = HttpStatusCode.Forbidden,
                            type = RemoteExceptionData::class.type,
                            value = RemoteExceptionData(
                                    type = ExceptionNames.ForbiddenException,
                                    message = "You do not have the permissions to perform this action.  Permissions you have:\n${(user as? HasPermissions)?.permissions?.joinToString() ?: "None"}\nPermissions required:\n${sf::class.permissions(classInfoRegistry).joinToString()}",
                                    trace = "",
                                    data = null
                            )
                    )
                }
                try {
                    val result = sf.invoke(user)
                    @Suppress("UNCHECKED_CAST")
                    call.respond(sf::class.returnType as Type<Any?>, result)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    val isExpected = sf::class.throwsTypes.contains(e.javaClass.simpleName)
                    call.respond(
                            status = if (isExpected) HttpStatusCode.BadRequest else HttpStatusCode.InternalServerError,
                            type = RemoteExceptionData::class.type,
                            value = RemoteExceptionData(
                                    type = e.javaClass.simpleName,
                                    message = e.message ?: "",
                                    trace = if (suppressStackTrace) "" else e.stackTraceString(),
                                    data = null
                            )
                    )
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                call.respond(
                        status = HttpStatusCode.InternalServerError,
                        type = RemoteExceptionData::class.type,
                        value = RemoteExceptionData(
                                type = e.javaClass.simpleName,
                                message = e.message ?: "",
                                trace = if (suppressStackTrace) "" else e.stackTraceString(),
                                data = null
                        )
                )
            }
        }

    }

    @ContextDsl
    fun Route.serverFunctions(path: String, suppressStackTrace: Boolean = true): Route {
        return post(path) { _ ->
            try {
                val sfs = call.receive(Request::class.type.list)
                val user = call.unwrappedPrincipal<USER>()
                sfs.find { sf -> !sf.permitted(classInfoRegistry, user as? HasPermissions) }?.let { sf ->
                    call.respond(
                            status = HttpStatusCode.Forbidden,
                            type = RemoteExceptionData::class.type,
                            value = RemoteExceptionData(
                                    type = ExceptionNames.ForbiddenException,
                                    message = "You do not have the permissions to perform this action.  Permissions you have:\n${(user as? HasPermissions)?.permissions?.joinToString() ?: "None"}\nPermissions required:\n${sf::class.permissions(classInfoRegistry).joinToString()}",
                                    trace = "",
                                    data = null
                            )
                    )
                }
                try {
                    val user = call.unwrappedPrincipal<USER>()
                    val result = sfs.map { it.invoke(user) }
                    call.respond(Any::class.typeNullable.list, result)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    val isExpected = sfs.any { sf -> sf::class.throwsTypes.contains(e.javaClass.simpleName) }
                    call.respond(
                            status = if (isExpected) HttpStatusCode.BadRequest else HttpStatusCode.InternalServerError,
                            type = RemoteExceptionData::class.type,
                            value = RemoteExceptionData(
                                    type = e.javaClass.simpleName,
                                    message = e.message ?: "",
                                    trace = if (suppressStackTrace) "" else e.stackTraceString(),
                                    data = null
                            )
                    )
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                call.respond(
                        status = HttpStatusCode.InternalServerError,
                        type = RemoteExceptionData::class.type,
                        value = RemoteExceptionData(
                                type = e.javaClass.simpleName,
                                message = e.message ?: "",
                                trace = if (suppressStackTrace) "" else e.stackTraceString(),
                                data = null
                        )
                )
            }
        }

    }

    private val KClassServerFunction_Returns = HashMap<KClass<*>, Type<*>>()
    val KClass<out Request<*>>.returnType: Type<*>
        get() {
            return KClassServerFunction_Returns.getOrPut(this) {
                (classInfoRegistry.getOrThrow(this)
                        .allImplements(classInfoRegistry)
                        .find { it.kClass == Request::class } ?: throw IllegalArgumentException("${this} does not appear to be a server function"))
                        .typeParameters.first().type
            }
        }

    private val KClassServerFunction_Throws = HashMap<KClass<*>, List<String>>()
    @Suppress("UNCHECKED_CAST")
    val KClass<out Request<*>>.throwsTypes: List<String>
        get() {
            return KClassServerFunction_Throws.getOrPut(this) {
                classInfoRegistry[this]!!
                        .implementsTree(classInfoRegistry)
                        .pathTo(Request::class)
                        ?.asSequence()
                        ?.flatMap {
                            it.info.annotations
                                    .find { it.name.endsWith("ThrowsTypes") }
                                    ?.arguments
                                    ?.asSequence()
                                    ?.mapNotNull { it as? String } ?: sequenceOf()
                        }
                        ?.toList() ?: listOf()
            }
        }

    private val KClassServerFunction_RequiresWrite = HashMap<KClass<*>, Boolean>()
    val KClass<out Request<*>>.requiresWrite: Boolean
        get() {
            return KClassServerFunction_RequiresWrite.getOrPut(this) {
                classInfoRegistry[this]!!
                        .implementsTree(classInfoRegistry)
                        .pathTo(Request::class)
                        ?.asSequence()
                        ?.any {
                            it.info.annotations.any { it.name.endsWith("Mutates") }
                        } ?: false
            }
        }

    private val KClassServerFunction_RequiresAtomicTransaction = HashMap<KClass<*>, Boolean>()
    var KClass<out Request<*>>.requiresAtomicTransaction: Boolean
        set(value) {
            KClassServerFunction_RequiresAtomicTransaction[this] = value
        }
        get() {
            return KClassServerFunction_RequiresAtomicTransaction.getOrPut(this) {
                classInfoRegistry[this]!!
                        .implementsTree(classInfoRegistry)
                        .pathTo(Request::class)
                        ?.asSequence()
                        ?.any {
                            it.info.annotations.any { it.name.endsWith("RequiresAtomicTransaction") }
                        } ?: false
            }
        }

    private val KClassServerFunction_Invocation = HashMap<KClass<*>, suspend (Any, USER?) -> Any?>()
    @Suppress("UNCHECKED_CAST")
    var <SF : Request<R>, R> KClass<SF>.invocation: suspend SF.(USER?) -> R
        set(value) {
            KClassServerFunction_Invocation[this] = value as suspend (Any, USER?) -> Any?
        }
        get() {
            return KClassServerFunction_Invocation.getOrPut(this) {
                classInfoRegistry[this]!!
                        .implementsTree(classInfoRegistry)
                        .pathTo(Request::class)
                        ?.asSequence()
                        ?.mapNotNull { KClassServerFunction_Invocation[it.info.kClass] }
                        ?.firstOrNull()
                        ?: throw IllegalArgumentException("No invocation could be found for the server function type $this")
            } as suspend SF.(USER?) -> R
        }

    @Suppress("UNCHECKED_CAST")
    fun <SF : Request<R>, R> invocation(forKclass: KClass<SF>, action: suspend SF.(USER?) -> R) {
        KClassServerFunction_Invocation[forKclass] = action as suspend (Any, USER?) -> Any?
    }

    @Suppress("UNCHECKED_CAST")
    suspend operator fun <R> Request<R>.invoke(user: USER?): R = KClassServerFunction_Invocation[this::class]!!.invoke(this, user) as R

}