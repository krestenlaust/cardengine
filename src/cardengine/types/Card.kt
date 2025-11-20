package cardengine.types

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

/*
class Card constructor(
    // Mostly static properties
    val baseCard: Card?, // None, if is template card
    val title: String,
    val cost: Int,
    val faction: cardengine.Faction?, // Can be neutral
    val specialCard: Boolean, // Gets discarded immediately after playing, can't have a strength.

    // dynamic properties
    val tags: MutableList<CardTag>,
    var power: Int?, // Artifacts don't have a strength and generally can't be destroyed
    var shield: Int,

    // Missing tags, effects, tier and rarity.
)*/