package cardengine.types

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