//Generated by Lightning Kite's Mirror plugin
//AUTOMATICALLY GENERATED AND WILL BE OVERRIDDEN IF THIS MESSAGE IS PRESENT
package com.lightningkite.mirror.archive.model

import com.lightningkite.mirror.info.*
import kotlin.reflect.KClass

@Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST", "USELESS_CAST")
object ConditionEqualToOneClassInfo: ClassInfo<Condition.EqualToOne<*>> {

    override val kClass: KClass<Condition.EqualToOne<*>> = Condition.EqualToOne::class
    override val modifiers: List<ClassInfo.Modifier> = listOf(ClassInfo.Modifier.Data)
    override val companion: Any? get() = null

    override val implements: List<Type<*>> = listOf(Type<com.lightningkite.mirror.archive.model.Condition<Any?>>(com.lightningkite.mirror.archive.model.Condition::class, listOf(TypeProjection(Type<Any?>(Any::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false))

    override val packageName: String = "com.lightningkite.mirror.archive.model"
    override val owner: KClass<*>? = Condition::class
    override val ownerName: String? = "Condition"

    override val name: String = "EqualToOne"
    override val annotations: List<AnnotationInfo> = listOf()
    override val enumValues: List<Condition.EqualToOne<*>>? = null

    val fieldValues = FieldInfo<Condition.EqualToOne<*>, kotlin.collections.List<Any?>>(this, "values", Type<kotlin.collections.List<Any?>>(kotlin.collections.List::class, listOf(TypeProjection(Type<Any?>(Any::class, listOf(), false), TypeProjection.Variance.INVARIANT)), false), false, { it.values as kotlin.collections.List<Any?>}, listOf())

    override val fields:List<FieldInfo<Condition.EqualToOne<*>, *>> = listOf(fieldValues)

    override fun construct(map: Map<String, Any?>): Condition.EqualToOne<Any?> {
        //Gather variables
        val values:kotlin.collections.List<Any?> = map["values"] as kotlin.collections.List<Any?>
        //Handle the optionals
        
        //Finally do the call
        return Condition.EqualToOne<Any?>(
            values = values
        )
    }

}