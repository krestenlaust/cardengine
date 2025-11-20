package cardengine

import cardengine.dsl.Ability

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
