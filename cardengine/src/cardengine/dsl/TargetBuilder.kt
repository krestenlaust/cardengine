package cardengine.dsl

// Targeting system
sealed class Target {
    object Self : Target()
    data class Selected(val filter: CardFilter) : Target() // Player chooses
    data class Random(val filter: CardFilter, val count: Int = 1) : Target()
    data class All(val filter: CardFilter) : Target()
    data class Highest(val filter: CardFilter, val count: Int = 1) : Target()
    data class Lowest(val filter: CardFilter, val count: Int = 1) : Target()
    data class Adjacent(val toSelf: Boolean = true) : Target()
}

class TargetBuilder {
    fun self() = Target.Self
    fun adjacent() = Target.Adjacent(true)
    fun random(count: Int = 1, block: FilterBuilder.() -> CardFilter) = Target.Random(FilterBuilder().block(), count)
    fun all(block: FilterBuilder.() -> CardFilter) = Target.All(FilterBuilder().block())
    fun highest(count: Int = 1, block: FilterBuilder.() -> CardFilter) = Target.Highest(FilterBuilder().block(), count)
    fun lowest(count: Int = 1, block: FilterBuilder.() -> CardFilter) = Target.Lowest(FilterBuilder().block(), count)
}
