package cardengine.dsl

// Conditions for triggers
sealed class Condition {
    data class OnDeploy(val filter: CardFilter = CardFilter.Any) : Condition()
    data class OnSummon(val filter: CardFilter = CardFilter.Any) : Condition()
    data class OnAllyDeploy(val filter: CardFilter = CardFilter.Any) : Condition()
    data class OnDamaged(val minAmount: Int = 1) : Condition()
    data class OnBoosted(val minAmount: Int = 1) : Condition()
    data class OnDestroyed(val filter: CardFilter = CardFilter.Any) : Condition()
    data class OnTurnStart(val ally: Boolean) : Condition()
    data class OnTurnEnd(val ally: Boolean) : Condition()
    data class OnRoundEnd : Condition()
    data class Multiple(val conditions: List<Condition>, val requireAll: Boolean = false) : Condition()
}

// Card filters for complex targeting
sealed class CardFilter {
    object Any : CardFilter()
    object Self : CardFilter()
    object Ally : CardFilter()
    object Enemy : CardFilter()
    data class InRow(val row: Row, val ally: Boolean) : CardFilter()
    data class WithTag(val tag: Tag) : CardFilter()
    data class OfFaction(val faction: Faction) : CardFilter()
    data class PowerEquals(val power: Int) : CardFilter()
    data class PowerGreaterThan(val power: Int) : CardFilter()
    data class PowerLessThan(val power: Int) : CardFilter()
    data class LowestPower(val ally: Boolean) : CardFilter()
    data class HighestPower(val ally: Boolean) : CardFilter()
    data class WithStatus(val statusType: String) : CardFilter()
    data class Not(val filter: CardFilter) : CardFilter()
    data class And(val filters: List<CardFilter>) : CardFilter()
    data class Or(val filters: List<CardFilter>) : CardFilter()
}