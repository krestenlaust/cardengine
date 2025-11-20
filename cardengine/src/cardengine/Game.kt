package cardengine

import cardengine.*

class Game {
    fun tick(state: GameState): GameState = when (state.phase) {

        GamePhase.StartTurn -> {
            val next = state.copy(
                phase = GamePhase.PlayerAction
            )
            next
        }

        GamePhase.PlayerAction -> {
            // Wait for user input like "play card", "use order"
            state
        }

        GamePhase.ResolveEffects -> {
            if (state.eventQueue.isEmpty()) {
                state.copy(phase = GamePhase.EndTurn)
            } else {
                val event = state.eventQueue.removeFirst()
                val updated = handleEvent(state, event)
                updated
            }
        }

        GamePhase.EndTurn -> {
            val nextPlayer = state.currentPlayer.other()
            state.copy(
                currentPlayer = nextPlayer,
                phase = GamePhase.StartTurn
            )
        }

        GamePhase.GameOver -> state
    }

    fun handleEvent(state: GameState, event: GameEvent): GameState =
        when (event) {

            is GameEvent.Deploy -> {
                val deployEvents = event.card.abilities.flatMap { it.deployEvents(event.card) }
                state.copy(eventQueue = listOf(deployEvents + state.eventQueue))
            }

            is GameEvent.Damaged -> {
                val updatedBoard = state.board.damage(event.target, event.amount)
                state.copy(board = updatedBoard)
            }

            is GameEvent.Boosted -> {
                val updatedBoard = state.board.boost(event.target, event.amount)
                state.copy(board = updatedBoard)
            }

            else -> state
        }

}
