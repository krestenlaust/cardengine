import cardengine.dsl.Row
import cardengine.dsl.Tag
import cardengine.dsl.ability

// Real Gwent card examples
fun main() {
    // Geralt of Rivia - Destroy enemy with 9+ power
    val geralt = ability {
        name = "Geralt of Rivia"
        description = "Deploy: Destroy an enemy unit with 9 or more power"
        deploy {
            destroy {
                highest(1) { enemy() and powerGreaterThan(8) }
            }
        }
    }

    // Yennefer of Vengerberg - Damage and heal
    val yennefer = ability {
        name = "Yennefer of Vengerberg"
        description = "Deploy: Damage an enemy by 5 and boost an ally by 5"
        deploy {
            multiple(
                damage(5) { highest(1) { enemy() } },
                boost(5) { lowest(1) { ally() } }
            )
        }
    }

    // Philippa Eilhart - Lock and damage
    val philippa = ability {
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

    // Imperial Golem - Shield on deploy
    val golem = ability {
        name = "Imperial Golem"
        description = "Deploy: Gain 2 Armor"
        deploy {
            applyArmor(2) { self() }
        }
    }

    // Viper Witcher - Poison
    val viper = ability {
        name = "Viper Witcher"
        description = "Deploy (Melee): Apply 3 Poison to an enemy"
        deploy {
            applyPoison(3) { highest(1) { enemy() } }
        }
    }

    // Blue Stripes Commando - Boost allies on deploy
    val commando = ability {
        name = "Blue Stripes Commando"
        description = "Whenever you play a Soldier, boost self by 1"
        trigger({ onAllyDeploy { withTag(Tag.SOLDIER) } }) {
            boost(1) { self() }
        }
    }

    // Arachas Queen - Spawn tokens on turn end
    val arachas = ability {
        name = "Arachas Queen"
        description = "At the end of your turn, spawn an Arachas Drone on this row"
        trigger({ onTurnEnd(ally = true) }) {
            spawn("arachas_drone", Row.MELEE)
        }
    }

    // Cahir - Repeat last Order
    val cahir = ability {
        name = "Cahir"
        description = "Order: Damage an enemy by 3"
        charges = 3
        order {
            damage(3) { highest(1) { enemy() } }
        }
    }

    // Wild Hunt Rider - Boost on frost
    val wildHunt = ability {
        name = "Wild Hunt Rider"
        description = "Whenever an enemy is damaged by a status, boost self by 1"
        trigger({ onDamaged() }) {
            boost(1) { self() }
        }
    }

    println("Modern Gwent cards defined:")
    listOf(geralt, yennefer, philippa, golem, viper, commando, arachas, cahir, wildHunt).forEach {
        println("- ${it.name}: ${it.description}")
    }
}