//Generated by Lightning Kite's Mirror plugin
//AUTOMATICALLY GENERATED AND WILL BE OVERRIDDEN IF THIS MESSAGE IS PRESENT
package com.lightningkite.mirror.archive.model

import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST", "USELESS_CAST")
object ConditionNotEqualClassInfo: ClassInfo<Condition.NotEqual<*>> {

    override val kClass: KClass<Condition.NotEqual<*>> = Condition.NotEqual::class
    override val modifiers: List<ClassInfo.Modifier> = listOf(ClassInfo.Modifier.Data)
    override val companion: Any? get() = null

    override val implements: List<Type<*>> = listOf(Type<com.lightningkite.mirror.archive.model.Condition<Any?>>(com.lightningkite.mirror.archive.model.Condition::class, listOf(TypeProjection(Type<Any?>(Any::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false))

    override val packageName: String = "com.lightningkite.mirror.archive.model"
    override val owner: KClass<*>? = Condition::class
    override val ownerName: String? = "Condition"

    override val name: String = "NotEqual"
    override val annotations: List<AnnotationInfo> = listOf()
    override val enumValues: List<Condition.NotEqual<*>>? = null

    val fieldValue = FieldInfo<Condition.NotEqual<*>, Any?>(this, "value", Type<Any?>(Any::class, listOf(), false), false, { it.value as Any?}, listOf())

    override val fields:List<FieldInfo<Condition.NotEqual<*>, *>> = listOf(fieldValue)

    override fun construct(map: Map<String, Any?>): Condition.NotEqual<Any?> {
        //Gather variables
        val value:Any? = map["value"] as Any?
        //Handle the optionals
        
        //Finally do the call
        return Condition.NotEqual<Any?>(
            value = value
        )
    }

}