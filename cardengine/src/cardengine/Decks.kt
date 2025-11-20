package cardengine

import cardengine.dsl.ability

object Decks {
    val StandardDeck = listOf(
        // Geralt of Rivia - already a Card
        Card(
            name = "Geralt of Rivia",
            basePower = 3,
            provisions = 9,
            faction = Faction.NEUTRAL,
            type = CardType.UNIT,
            tags = setOf(Tag.WITCHER),
            abilities = listOf(
                ability {
                    name = "Geralt of Rivias sword"
                    description = "Deploy: Destroy an enemy unit with 9 or more power"
                    deploy {
                        destroy {
                            highest(1) { enemy() and powerGreaterThan(8) }
                        }
                    }
                }
            )
        ),

        // Yennefer of Vengerberg
        Card(
            name = "Yennefer of Vengerberg",
            basePower = 5,
            provisions = 10,
            faction = Faction.NEUTRAL,
            type = CardType.UNIT,
            tags = setOf(Tag.MAGE),
            abilities = listOf(
                ability {
                    name = "Yennefer of Vengerberg"
                    description = "Deploy: Damage an enemy by 5 and boost an ally by 5"
                    deploy {
                        multiple(
                            damage(5) { highest(1) { enemy() } },
                            boost(5) { lowest(1) { ally() } }
                        )
                    }
                }
            )
        ),

        // Philippa Eilhart
        Card(
            name = "Philippa Eilhart",
            basePower = 4,
            provisions = 9,
            faction = Faction.NEUTRAL,
            type = CardType.UNIT,
            tags = setOf(Tag.MAGE),
            abilities = listOf(
                ability {
                    name = "Philippa Eilhart"
                    description = "Order: Lock an enemy unit then damage it by 4"
                    charges = 2
                    order {
                        multiple(
                            lock { highest(1) { enemy() } },
                            damage(4) { highest(1) { enemy() } }
                        )
                    }
                }
            )
        ),

        // Viper Witcher
        Card(
            name = "Viper Witcher",
            basePower = 3,
            provisions = 6,
            faction = Faction.NILFGAARD,
            type = CardType.UNIT,
            tags = setOf(Tag.WITCHER),
            abilities = listOf(
                ability {
                    name = "Viper Witcher"
                    description = "Deploy (Melee): Apply 3 Poison to an enemy"
                    deploy {
                        applyPoison(3) { highest(1) { enemy() } }
                    }
                }
            )
        ),

        // Blue Stripes Commando
        Card(
            name = "Blue Stripes Commando",
            basePower = 4,
            provisions = 5,
            faction = Faction.NORTHERN_REALMS,
            type = CardType.UNIT,
            tags = setOf(Tag.SOLDIER),
            abilities = listOf(
                ability {
                    name = "Blue Stripes Commando"
                    description = "Whenever you play a Soldier, boost self by 1"
                    trigger({ onAllyDeploy { withTag(Tag.SOLDIER) } }) {
                        boost(1) { self() }
                    }
                }
            )
        ),
    )
}
