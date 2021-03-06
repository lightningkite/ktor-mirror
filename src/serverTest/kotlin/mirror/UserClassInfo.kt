//Generated by Lightning Kite's Mirror plugin
//AUTOMATICALLY GENERATED AND WILL BE OVERRIDDEN IF THIS MESSAGE IS PRESENT
package com.lightningkite.mirror.server

import com.lightningkite.kommon.exception.ExceptionNames
import com.lightningkite.lokalize.time.TimeStamp
import com.lightningkite.mirror.archive.server.security.HasPassword
import com.lightningkite.mirror.info.Indexed
import com.lightningkite.mirror.info.ThrowsTypes
import com.lightningkite.mirror.request.Request
import com.lightningkite.mirror.archive.database.*
import com.lightningkite.mirror.archive.model.*
import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST", "USELESS_CAST")
object UserClassInfo: ClassInfo<User> {

    override val kClass: KClass<User> = User::class
    override val modifiers: List<ClassInfo.Modifier> = listOf(ClassInfo.Modifier.Data)
    override val companion: Any? get() = null

    override val implements: List<Type<*>> = listOf(Type<HasId>(HasId::class, listOf(), false), Type<HasPassword>(HasPassword::class, listOf(), false))

    override val packageName: String = "com.lightningkite.mirror.server"
    override val owner: KClass<*>? = null
    override val ownerName: String? = null

    override val name: String = "User"
    override val annotations: List<AnnotationInfo> = listOf()
    override val enumValues: List<User>? = null

    val fieldId = FieldInfo<User, Id>(this, "id", Type<Id>(Id::class, listOf(), false), false, { it.id as Id}, listOf())
    val fieldEmail = FieldInfo<User, String>(this, "email", Type<String>(String::class, listOf(), false), false, { it.email as String}, listOf(AnnotationInfo("@Indexed", listOf())))
    val fieldPassword = FieldInfo<User, String>(this, "password", Type<String>(String::class, listOf(), false), false, { it.password as String}, listOf())
    val fieldRole = FieldInfo<User, User.Role>(this, "role", Type<User.Role>(User.Role::class, listOf(), false), false, { it.role as User.Role}, listOf())
    val fieldRejectTokensBefore = FieldInfo<User, TimeStamp>(this, "rejectTokensBefore", Type<TimeStamp>(TimeStamp::class, listOf(), false), false, { it.rejectTokensBefore as TimeStamp}, listOf())

    override val fields:List<FieldInfo<User, *>> = listOf(fieldId, fieldEmail, fieldPassword, fieldRole, fieldRejectTokensBefore)

    override fun construct(map: Map<String, Any?>): User {
        //Gather variables
        val id:Id = map["id"] as Id
        val email:String = map["email"] as String
        val password:String = map["password"] as String
        val role:User.Role = map["role"] as User.Role
        val rejectTokensBefore:TimeStamp = map["rejectTokensBefore"] as TimeStamp
        //Handle the optionals
        
        //Finally do the call
        return User(
            id = id,
            email = email,
            password = password,
            role = role,
            rejectTokensBefore = rejectTokensBefore
        )
    }

}