import cardengine.Decks
import cardengine.Game
import cardengine.GamePhase
import cardengine.GameState
import cardengine.Row

fun main() {
    test()
}


fun test() {
    val card1 = Decks.StandardDeck[0]
    val card2 = Decks.StandardDeck[1]

    val initialGameState = GameState(
        playerCount = 2,
        phase = GamePhase.StartTurn,
        boards = mapOf(
            0 to mapOf(
                Row.MELEE to listOf(card1),
            ),
            1 to mapOf(
                Row.RANGED to listOf(card2)
            )
        ),
        hands = mapOf(
            0 to listOf(),
            1 to listOf()
        ),
        decks = mapOf(
            0 to listOf(card1),
            1 to listOf(card2)
        ),
    )

    val game = Game()

    var gameState = game.tick(initialGameState)
    gameState = game.tick(initialGameState)
    gameState = game.tick(initialGameState)
}
