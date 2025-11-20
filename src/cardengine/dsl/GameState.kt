package cardengine.dsl

import cardengine.Card
import cardengine.Row

data class GameState(
    val playerBoard: MutableMap<Row, MutableList<Card>> = mutableMapOf(
        Row.MELEE to mutableListOf(),
        Row.RANGED to mutableListOf()
    ),
    val opponentBoard: MutableMap<Row, MutableList<Card>> = mutableMapOf(
        Row.MELEE to mutableListOf(),
        Row.RANGED to mutableListOf()
    ),
    val hand: MutableList<Card> = mutableListOf(),
    val deck: MutableList<Card> = mutableListOf(),
    val graveyard: MutableList<Card> = mutableListOf(),
    val opponentGraveyard: MutableList<Card> = mutableListOf()
) {
    fun allPlayerCards() = playerBoard.values.flatten()
    fun allOpponentCards() = opponentBoard.values.flatten()
    fun allCards() = allPlayerCards() + allOpponentCards()
}