package dius.dennis.tennis

enum class SetState {
    WIN_PLAYER_ONE,
    WIN_PLAYER_TWO,
    ACTIVE,
    TIE_BREAK,
    TIE;

    override fun toString(): String {
        val strings = mapOf(
                WIN_PLAYER_ONE to "set win player one",
                WIN_PLAYER_TWO to "set win player two"
        )
        return strings[this]!!
    }
}