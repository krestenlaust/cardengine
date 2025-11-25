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

    val currentPlayer: PlayerId = 0,
    val phase: GamePhase = GamePhase.GameStarted,

    val eventQueue: List<GameEvent> = emptyList(),

    val hands: Map<PlayerId, List<Card>> = (1..playerCount).associateWith { playerId ->
        emptyList()
    },
    val decks: Map<PlayerId, List<Card>> = (1..playerCount).associateWith { playerId ->
        emptyList()
    },
    val graveyards: Map<PlayerId, List<Card>> = (1..playerCount).associateWith { playerId ->
        emptyList()
    },

    val turnNumber: Int = 1,
) {
    /*fun DamageCard(target: Card,) {
        this.copy(boards = boards.map { it.value.map { it.value.map {  } } })
    }*/
}
