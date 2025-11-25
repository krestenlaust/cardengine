package cardengine

import cardengine.dsl.Effect

class Game {
    fun tick(state: GameState): GameState = when (state.phase) {
        GamePhase.GameStarted -> {
            state.copy(phase = GamePhase.StartTurn)
        }

        GamePhase.GameEnded -> {
            state
        }

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
            if (state.effectQueue.isEmpty()) {
                state.copy(phase = GamePhase.EndTurn)
            } else {
                val nextEvent = state.effectQueue.first()
                val newState = state.copy(effectQueue = state.effectQueue.drop(1))
                handleEffect(newState, nextEvent)
            }
        }

        GamePhase.EndTurn -> {
            println("End turn")
            val nextPlayer = (state.currentPlayer + 1) % state.playerCount;
            state.copy(
                currentPlayer = nextPlayer,
                phase = GamePhase.StartTurn
            )
        }
    }

    fun handleEffect(state: GameState, effect: Effect): GameState =
        when (effect) {
            is Effect.Damage -> {
                state
                //val updatedBoard = state.board.damage(effect.target, effect.amount)
                //state.copy(board = updatedBoard)
            }

            is Effect.Boost -> {
                state
                //val updatedBoard = state.board.boost(effect.target, effect.amount)
                //state.copy(board = updatedBoard)
            }

            else -> state
        }

}
