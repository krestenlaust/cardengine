package cardengine

data class GameState(
    val playerBoard: Map<Row, List<Card>> = mapOf(
        Row.MELEE to listOf(),
        Row.RANGED to listOf()
    ),
    val opponentBoard: Map<Row, List<Card>> = mapOf(
        Row.MELEE to listOf(),
        Row.RANGED to listOf()
    ),
    val hand: List<Card> = listOf(),
    val deck: List<Card> = listOf(),
    val graveyard: List<Card> = listOf(),
    val opponentGraveyard: List<Card> = listOf()
) {
    fun allPlayerCards() = playerBoard.values.flatten()
    fun allOpponentCards() = opponentBoard.values.flatten()
    fun allCards() = allPlayerCards() + allOpponentCards()
}