package cardengine.dsl

import cardengine.*

// Ability types
sealed class AbilityType {
    object Deploy : AbilityType()  // Triggers when played from hand
    object Order : AbilityType()   // Manual activation
    object Passive : AbilityType() // Always active
    object Deathwish : AbilityType() // Triggers on destroy
    object Ranged : AbilityType()  // Order with 1 turn cooldown
    object Melee : AbilityType()   // Order, no cooldown
    data class Trigger(val condition: Condition) : AbilityType() // Event-based
}

// Ability definition
data class Ability(
    val name: String,
    val type: AbilityType,
    val effect: Effect,
    val charges: Int = 1,
    val description: String = ""
)

// DSL Builders
class AbilityBuilder {
    var name: String = ""
    var description: String = ""
    var charges: Int = 1
    private var abilityType: AbilityType? = null
    private var effect: Effect? = null

    fun deploy(block: EffectBuilder.() -> Effect) {
        abilityType = AbilityType.Deploy
        effect = EffectBuilder().block()
    }

    fun order(block: EffectBuilder.() -> Effect) {
        abilityType = AbilityType.Order
        effect = EffectBuilder().block()
    }

    fun passive(block: EffectBuilder.() -> Effect) {
        abilityType = AbilityType.Passive
        effect = EffectBuilder().block()
    }

    fun deathwish(block: EffectBuilder.() -> Effect) {
        abilityType = AbilityType.Deathwish
        effect = EffectBuilder().block()
    }

    fun trigger(condition: TriggerBuilder.() -> Condition, block: EffectBuilder.() -> Effect) {
        abilityType = AbilityType.Trigger(TriggerBuilder().condition())
        effect = EffectBuilder().block()
    }

    fun build(): Ability {
        require(abilityType != null) { "Ability must have a type" }
        require(effect != null) { "Ability must have an effect" }
        return Ability(name, abilityType!!, effect!!, charges, description)
    }
}

fun ability(init: AbilityBuilder.() -> Unit): Ability = AbilityBuilder().apply(init).build()
