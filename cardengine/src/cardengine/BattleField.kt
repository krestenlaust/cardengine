package cardengine

/**
 * One of the sides in a battle, the board.
 */
class BattleField : CardField {
    override fun PutCard(card: Card?) {
        TODO("Not yet implemented")
    }

    override fun GetCard(): Card? {
        TODO("Not yet implemented")
    }

    val MaxCardsPerRow: Int;
    val RowCount: Int;
}
