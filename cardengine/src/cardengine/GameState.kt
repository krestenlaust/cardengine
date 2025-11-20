package cardengine

typealias PlayerId = Int

data class GameState(
    val playerCount: Int,
    val boards: Map<PlayerId, Map<Row, List<Card>>> =
        (1..playerCount).associateWith { playerId ->
            mapOf(
                Row.MELEE to emptyList(),
                Row.RANGED to emptyList()
            )
        },

    val currentPlayer: PlayerId,
    val phase: GamePhase,

    val eventQueue: List<GameEvent>,

    val hands: Map<PlayerId, List<Card>>,
    val decks: Map<PlayerId, List<Card>>,
    val graveyards: Map<PlayerId, List<Card>>,

    val graveyard: List<Card> = listOf(),
    val opponentGraveyard: List<Card> = listOf(),
    val turnNumber: Int = 1,
)
