package dius.dennis.tennis

enum class Player {
    ONE,
    TWO;

    override fun toString(): String {
        val strings = mapOf(Pair(ONE, "Player One"), Pair(TWO, "Player Two"))
        return strings[this]!!
    }

}
