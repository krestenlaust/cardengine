package cardengine.dsl

class TriggerBuilder {
    fun onDeploy(block: FilterBuilder.() -> CardFilter = { any() }) = Condition.OnDeploy(FilterBuilder().block())
    fun onAllyDeploy(block: FilterBuilder.() -> CardFilter = { any() }) = Condition.OnAllyDeploy(FilterBuilder().block())
    fun onDamaged(minAmount: Int = 1) = Condition.OnDamaged(minAmount)
    fun onBoosted(minAmount: Int = 1) = Condition.OnBoosted(minAmount)
    fun onDestroyed(block: FilterBuilder.() -> CardFilter = { any() }) = Condition.OnDestroyed(FilterBuilder().block())
    fun onTurnStart(ally: Boolean = true) = Condition.OnTurnStart(ally)
    fun onTurnEnd(ally: Boolean = true) = Condition.OnTurnEnd(ally)
}
