package cardengine.dsl

// Core game types for modern Gwent
enum class Row { MELEE, RANGED }
enum class Faction { NORTHERN_REALMS, NILFGAARD, MONSTERS, SCOIATAEL, SKELLIGE, SYNDICATE, NEUTRAL }
enum class CardType { UNIT, SPECIAL, ARTIFACT, STRATAGEM }
enum class Tag { SOLDIER, MAGE, BEAST, VAMPIRE, WITCHER, ARISTOCRAT, DWARF, ELF }

// Status effects
data class Status(
    var armor: Int = 0,
    var shield: Int = 0,
    var vitality: Int = 0,
    var bleeding: Int = 0,
    var poison: Int = 0,
    var lock: Boolean = false,
    var doom: Int = 0
)

data class Card(
    val id: String,
    val name: String,
    val basePower: Int,
    var power: Int = basePower,
    val provisions: Int,
    val faction: Faction,
    val type: CardType = CardType.UNIT,
    val tags: Set<Tag> = emptySet(),
    val status: Status = Status(),
    val abilities: List<Ability> = emptyList()
) {
    fun hasTag(tag: Tag) = tags.contains(tag)
}

// Game state
data class GameState(
    val playerBoard: MutableMap<Row, MutableList<Card>> = mutableMapOf(
        Row.MELEE to mutableListOf(),
        Row.RANGED to mutableListOf()
    ),
    val opponentBoard: MutableMap<Row, MutableList<Card>> = mutableMapOf(
        Row.MELEE to mutableListOf(),
        Row.RANGED to mutableListOf()
    ),
    val hand: MutableList<Card> = mutableListOf(),
    val deck: MutableList<Card> = mutableListOf(),
    val graveyard: MutableList<Card> = mutableListOf(),
    val opponentGraveyard: MutableList<Card> = mutableListOf()
) {
    fun allPlayerCards() = playerBoard.values.flatten()
    fun allOpponentCards() = opponentBoard.values.flatten()
    fun allCards() = allPlayerCards() + allOpponentCards()
}

// Event system for triggers
sealed class GameEvent {
    data class Deploy(val card: Card, val row: Row) : GameEvent()
    data class Summon(val card: Card, val row: Row) : GameEvent() // played without Deploy
    data class Damaged(val card: Card, val amount: Int, val source: Card?) : GameEvent()
    data class Boosted(val card: Card, val amount: Int, val source: Card?) : GameEvent()
    data class Destroyed(val card: Card, val row: Row) : GameEvent()
    data class TurnStart(val isPlayer: Boolean) : GameEvent()
    data class TurnEnd(val isPlayer: Boolean) : GameEvent()
    data class RoundEnd(val winner: Boolean?) : GameEvent()
    data class CardDrawn(val card: Card) : GameEvent()
    data class StatusApplied(val card: Card, val statusType: String) : GameEvent()
}

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

