package dius.dennis.tennis

enum class GameState {
    ADVANTAGE_PLAYER_ONE,
    ADVANTAGE_PLAYER_TWO,
    WIN_PLAYER_ONE,
    WIN_PLAYER_TWO,
    DEUCE;

    override fun toString(): String {
        val strings = mapOf(
                ADVANTAGE_PLAYER_ONE to "advantage player one",
                ADVANTAGE_PLAYER_TWO to "advantage player two",
                WIN_PLAYER_ONE to "win player one",
                WIN_PLAYER_TWO to "win player two",
                DEUCE to "deuce"
        )
        return strings[this]!!
    }
}