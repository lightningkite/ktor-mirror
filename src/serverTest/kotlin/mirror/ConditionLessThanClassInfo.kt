//Generated by Lightning Kite's Mirror plugin
//AUTOMATICALLY GENERATED AND WILL BE OVERRIDDEN IF THIS MESSAGE IS PRESENT
package com.lightningkite.mirror.archive.model

import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST", "USELESS_CAST")
object ConditionLessThanClassInfo: ClassInfo<Condition.LessThan<*>> {

    override val kClass: KClass<Condition.LessThan<*>> = Condition.LessThan::class
    override val modifiers: List<ClassInfo.Modifier> = listOf(ClassInfo.Modifier.Data)
    override val companion: Any? get() = null

    override val implements: List<Type<*>> = listOf(Type<com.lightningkite.mirror.archive.model.Condition<kotlin.Comparable<kotlin.Comparable<*>>>>(com.lightningkite.mirror.archive.model.Condition::class, listOf(TypeProjection(Type<kotlin.Comparable<kotlin.Comparable<*>>>(kotlin.Comparable::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false))

    override val packageName: String = "com.lightningkite.mirror.archive.model"
    override val owner: KClass<*>? = Condition::class
    override val ownerName: String? = "Condition"

    override val name: String = "LessThan"
    override val annotations: List<AnnotationInfo> = listOf()
    override val enumValues: List<Condition.LessThan<*>>? = null

    val fieldValue = FieldInfo<Condition.LessThan<*>, kotlin.Comparable<kotlin.Comparable<*>>>(this, "value", Type<kotlin.Comparable<kotlin.Comparable<*>>>(kotlin.Comparable::class, listOf(), false), false, { it.value as kotlin.Comparable<kotlin.Comparable<*>>}, listOf())

    override val fields:List<FieldInfo<Condition.LessThan<*>, *>> = listOf(fieldValue)

    override fun construct(map: Map<String, Any?>): Condition.LessThan<kotlin.Comparable<kotlin.Comparable<*>>> {
        //Gather variables
        val value:kotlin.Comparable<kotlin.Comparable<*>> = map["value"] as kotlin.Comparable<kotlin.Comparable<*>>
        //Handle the optionals
        
        //Finally do the call
        return Condition.LessThan<kotlin.Comparable<kotlin.Comparable<*>>>(
            value = value
        )
    }

}