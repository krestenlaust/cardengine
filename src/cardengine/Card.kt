package cardengine

class Card constructor(
    baseCard: Card?, // None, if is template card

    // Mostly static properties
    title: String,
    strength: Int,
    cost: Int,
    faction: Faction?, // Can be neutral

    // Mostly dynamic properties
    shield: Int,

    // Missing tags, effects, tier and rarity.
) {

}