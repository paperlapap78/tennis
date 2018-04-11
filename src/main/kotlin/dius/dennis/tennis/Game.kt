package dius.dennis.tennis

open class Game {

    private val pointScore = mapOf(0 to "0", 1 to "15", 2 to "30", 3 to "40")
    protected var points = mutableMapOf(Player.ONE to 0, Player.TWO to 0)

    fun pointWonBy(player: Player) {
       points[player] = points[player]!! + 1
    }

    open fun score() = when {
        isDeuce() -> "${GameState.DEUCE}"
        isLead() -> {
            val difference = points[Player.ONE]!! - points[Player.TWO]!!
            when {
                difference == 1 -> "${GameState.ADVANTAGE_PLAYER_ONE}"
                difference == -1 -> "${GameState.ADVANTAGE_PLAYER_TWO}"
                difference >= 2 -> "${GameState.WIN_PLAYER_ONE}"
                else -> "${GameState.WIN_PLAYER_TWO}"
            }
        }
        else -> "${pointScore[points[Player.ONE]]!!}-${pointScore[points[Player.TWO]]!!}"
    }

    private fun isDeuce() = points[Player.ONE]!! == points[Player.TWO]!! && points[Player.ONE]!! >= 3

    protected open fun isLead() = points[Player.ONE]!! >= 4 || points[Player.TWO]!! >= 4


    fun clear() {
        points[Player.ONE] = 0
        points[Player.TWO] = 0
    }
}



