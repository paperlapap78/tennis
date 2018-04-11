package dius.dennis.tennis

class MatchSet {

    private val matchSets = mutableListOf(Game())

    fun clear() {
        matchSets.clear()
        matchSets.add(Game())
    }

    fun pointWonBy(player: Player) {
        matchSets.last().pointWonBy(player)
        when(state()) {
            SetState.ACTIVE -> when (matchSets.last().score()) {
                    "${GameState.WIN_PLAYER_ONE}",
                    "${GameState.WIN_PLAYER_TWO}" -> matchSets.add(Game())
            }
            SetState.TIE_BREAK -> when (matchSets.last().score()) {
                "${GameState.WIN_PLAYER_ONE}",
                "${GameState.WIN_PLAYER_TWO}" -> matchSets.add(TieBreakGame())
            }
            else -> {
                // Game Over
            }
        }
    }

    fun score() = "${winsPlayerOne()}-${winsPlayerTwo()}, ${when(state()) {
        SetState.WIN_PLAYER_ONE -> "${SetState.WIN_PLAYER_ONE}"
        SetState.WIN_PLAYER_TWO -> "${SetState.WIN_PLAYER_TWO}"
        else -> matchSets.last().score()
    }}"

    private fun state(): SetState {
        val winsPlayerOne = winsPlayerOne()
        val winsPlayerTwo = winsPlayerTwo()
        val lead = winsPlayerOne - winsPlayerTwo
        return when {
            winsPlayerOne >= 6 && lead >= 2
                    || winsPlayerOne == 7 && lead == 1 -> SetState.WIN_PLAYER_ONE
            winsPlayerTwo >= 6 && lead <= -2
                    || winsPlayerTwo == 7 && lead == -1 -> SetState.WIN_PLAYER_TWO
            winsPlayerOne == 6 && winsPlayerTwo == 6 -> SetState.TIE_BREAK
            else -> SetState.ACTIVE
        }
    }

    private fun winsPlayerTwo() = matchSets.filter { game -> game.score() == "${GameState.WIN_PLAYER_TWO}" }.size

    private fun winsPlayerOne() = matchSets.filter { game -> game.score() == "${GameState.WIN_PLAYER_ONE}" }.size
}

