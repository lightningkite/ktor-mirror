//Generated by Lightning Kite's Mirror plugin
//AUTOMATICALLY GENERATED AND WILL BE OVERRIDDEN IF THIS MESSAGE IS PRESENT
package com.lightningkite.mirror.archive.database

import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST", "USELESS_CAST")
object SuspendMapQueryRequestClassInfo: ClassInfo<SuspendMapQueryRequest<*,*>> {

    override val kClass: KClass<SuspendMapQueryRequest<*,*>> = SuspendMapQueryRequest::class
    override val modifiers: List<ClassInfo.Modifier> = listOf(ClassInfo.Modifier.Interface, ClassInfo.Modifier.Abstract)
    override val companion: Any? get() = null

    override val implements: List<Type<*>> = listOf(Type<com.lightningkite.mirror.request.Request<kotlin.collections.List<com.lightningkite.mirror.archive.database.SuspendMap.Entry<Any?, kotlin.Any>>>>(com.lightningkite.mirror.request.Request::class, listOf(TypeProjection(Type<kotlin.collections.List<com.lightningkite.mirror.archive.database.SuspendMap.Entry<Any?, kotlin.Any>>>(kotlin.collections.List::class, listOf(TypeProjection(Type<com.lightningkite.mirror.archive.database.SuspendMap.Entry<Any?, kotlin.Any>>(com.lightningkite.mirror.archive.database.SuspendMap.Entry::class, listOf(TypeProjection(Type<Any?>(Any::class, listOf(), false), TypeProjection.Variance.INVARIANT), TypeProjection(Type<kotlin.Any>(kotlin.Any::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false), TypeProjection.Variance.INVARIANT)), false), TypeProjection.Variance.INVARIANT)), false), Type<com.lightningkite.mirror.archive.database.SuspendMapServerFunction<Any?, kotlin.Any>>(com.lightningkite.mirror.archive.database.SuspendMapServerFunction::class, listOf(TypeProjection(Type<Any?>(Any::class, listOf(), false), TypeProjection.Variance.INVARIANT), TypeProjection(Type<kotlin.Any>(kotlin.Any::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false))

    override val packageName: String = "com.lightningkite.mirror.archive.database"
    override val owner: KClass<*>? = null
    override val ownerName: String? = null

    override val name: String = "SuspendMapQueryRequest"
    override val annotations: List<AnnotationInfo> = listOf()
    override val enumValues: List<SuspendMapQueryRequest<*,*>>? = null

    

    override val fields:List<FieldInfo<SuspendMapQueryRequest<*,*>, *>> = listOf()

    override fun construct(map: Map<String, Any?>): SuspendMapQueryRequest<Any?, kotlin.Any> = throw NotImplementedError()

}