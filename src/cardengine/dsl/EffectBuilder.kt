package cardengine.dsl

import cardengine.types.*

// Effects in modern Gwent
sealed class Effect {
    data class Damage(val target: Target, val amount: Int) : Effect()
    data class Boost(val target: Target, val amount: Int) : Effect()
    data class Heal(val target: Target, val amount: Int) : Effect()
    data class Destroy(val target: Target) : Effect()
    data class Banish(val target: Target) : Effect() // Remove from game
    data class Lock(val target: Target) : Effect()
    data class Purify(val target: Target) : Effect() // Remove statuses
    data class ApplyArmor(val target: Target, val amount: Int) : Effect()
    data class ApplyShield(val target: Target, val amount: Int) : Effect()
    data class ApplyVitality(val target: Target, val amount: Int) : Effect()
    data class ApplyBleeding(val target: Target, val amount: Int) : Effect()
    data class ApplyPoison(val target: Target, val amount: Int) : Effect()
    data class ApplyDoom(val target: Target, val turns: Int) : Effect()
    data class Draw(val count: Int) : Effect()
    data class PlayFromDeck(val filter: CardFilter) : Effect()
    data class Spawn(val cardId: String, val row: Row) : Effect()
    data class Summon(val filter: CardFilter, val row: Row) : Effect() // From deck/graveyard
    data class MoveTo(val target: Target, val row: Row) : Effect()
    data class Transform(val target: Target, val intoCardId: String) : Effect()
    data class Seize(val target: Target) : Effect() // Take control
    data class Swap(val target: Target, val with: Target) : Effect()
    data class Multiple(val effects: List<Effect>) : Effect()
    data class Conditional(val condition: (GameState) -> Boolean, val ifTrue: Effect, val ifFalse: Effect? = null) : Effect()
    data class Repeat(val effect: Effect, val times: Int) : Effect()
}

class EffectBuilder {
    fun damage(amount: Int, block: TargetBuilder.() -> Target) = Effect.Damage(TargetBuilder().block(), amount)
    fun boost(amount: Int, block: TargetBuilder.() -> Target) = Effect.Boost(TargetBuilder().block(), amount)
    fun heal(amount: Int, block: TargetBuilder.() -> Target) = Effect.Heal(TargetBuilder().block(), amount)
    fun destroy(block: TargetBuilder.() -> Target) = Effect.Destroy(TargetBuilder().block())
    fun lock(block: TargetBuilder.() -> Target) = Effect.Lock(TargetBuilder().block())
    fun applyBleeding(amount: Int, block: TargetBuilder.() -> Target) = Effect.ApplyBleeding(TargetBuilder().block(), amount)
    fun applyPoison(amount: Int, block: TargetBuilder.() -> Target) = Effect.ApplyPoison(TargetBuilder().block(), amount)
    fun applyVitality(amount: Int, block: TargetBuilder.() -> Target) = Effect.ApplyVitality(TargetBuilder().block(), amount)
    fun applyArmor(amount: Int, block: TargetBuilder.() -> Target) = Effect.ApplyArmor(TargetBuilder().block(), amount)
    fun draw(count: Int) = Effect.Draw(count)
    fun spawn(cardId: String, row: Row) = Effect.Spawn(cardId, row)
    fun seize(block: TargetBuilder.() -> Target) = Effect.Seize(TargetBuilder().block())

    fun multiple(vararg effects: Effect) = Effect.Multiple(effects.toList())
    fun repeat(times: Int, effect: Effect) = Effect.Repeat(effect, times)
}
