package cardengine.dsl

import cardengine.types.*

class FilterBuilder {
    fun any() = CardFilter.Any
    fun ally() = CardFilter.Ally
    fun enemy() = CardFilter.Enemy
    fun inRow(row: Row, ally: Boolean = true) = CardFilter.InRow(row, ally)
    fun withTag(tag: Tag) = CardFilter.WithTag(tag)
    fun ofFaction(faction: Faction) = CardFilter.OfFaction(faction)
    fun lowestPower(ally: Boolean = true) = CardFilter.LowestPower(ally)
    fun highestPower(ally: Boolean = true) = CardFilter.HighestPower(ally)
    fun powerGreaterThan(power: Int) = CardFilter.PowerGreaterThan(power)

    infix fun CardFilter.and(other: CardFilter) = CardFilter.And(listOf(this, other))
    infix fun CardFilter.or(other: CardFilter) = CardFilter.Or(listOf(this, other))
}
