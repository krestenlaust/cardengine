package cardengine

class Card constructor(
    // Mostly static properties
    val baseCard: Card?, // None, if is template card
    val title: String,
    val cost: Int,
    val faction: Faction?, // Can be neutral
    val specialCard: Boolean, // Gets discarded immediately after playing, can't have a strength.

    // dynamic properties
    val tags: MutableList<CardTag>,
    var power: Int?, // Artifacts don't have a strength and generally can't be destroyed
    var shield: Int,

    // Missing tags, effects, tier and rarity.
)