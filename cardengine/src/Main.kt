import cardengine.dsl.ability

import cardengine.*

// Real Gwent card examples
fun main() {
    println("Modern Gwent cards defined:")
    Decks.StandardDeck.forEach {
        println("- ${it.name}: ${it.abilities}")
    }
}