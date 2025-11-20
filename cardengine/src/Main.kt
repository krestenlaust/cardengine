import cardengine.dsl.ability

import cardengine.*

// Real Gwent card examples
fun main() {
    println("Modern Gwent cards defined:")
    listOf(geralt, yennefer, philippa, golem, viper, commando, arachas, cahir, wildHunt).forEach {
        println("- ${it.name}: ${it.description}")
    }
}