package dius.dennis.tennis

class TieBreakGame: Game() {

    override fun score(): String {
        val lead = points[Player.ONE]!! - points[Player.TWO]!!
        return when {
            points[Player.ONE]!! >= 7 && lead >= 2 -> "${GameState.WIN_PLAYER_ONE}"
            points[Player.TWO]!! >= 7 && lead <= -2 -> "${GameState.WIN_PLAYER_TWO}"
            else -> "${points[Player.ONE]!!}-${points[Player.TWO]!!}"
        }
    }
}