package dius.dennis.tennis

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object TieBreakGameSpec: Spek({

    describe("a tie break game with two players") {
        val game = TieBreakGame()

        afterEachTest {
            game.clear()
        }

        context("player one scores") {
            beforeEachTest {
                game.pointWonBy(Player.ONE)
            }

            it("shows player on in the lead with 1-0") {
                game.score() shouldEqual "1-0"
            }
        }

        context("player one scores 7 points") {
            beforeEachTest {
                for (i in 1..7) game.pointWonBy(Player.ONE)
            }

            context("and 2 points more than player two") {
                beforeEachTest {
                    for (i in 1..5) game.pointWonBy(Player.TWO)
                }

                it("player one wins game") {
                    game.score() shouldEqual "${GameState.WIN_PLAYER_ONE}"
                }
            }

            context("and only 1 point more than player two") {
                beforeEachTest {
                    for (i in 1..6) game.pointWonBy(Player.TWO)
                }

                it("game continues") {
                    game.score() shouldEqual "7-6"
                }

                context("and player one scores a 2 point lead") {
                    beforeEachTest {
                        game.pointWonBy(Player.ONE)
                    }

                    it("player one wins game") {
                        game.score() shouldEqual "${GameState.WIN_PLAYER_ONE}"
                    }
                }
            }
        }

        context("player two scores 7 points") {
            beforeEachTest {
                for (i in 1..7) game.pointWonBy(Player.TWO)
            }

            context("and 2 points more than opponent") {
                beforeEachTest {
                    for (i in 1..5) game.pointWonBy(Player.ONE)
                }

                it("player two wins game") {
                    game.score() shouldEqual "${GameState.WIN_PLAYER_TWO}"
                }
            }
            context("and only 1 point more than opponent") {
                beforeEachTest {
                    for (i in 1..6) game.pointWonBy(Player.ONE)
                }

                it("game continues") {
                    game.score() shouldEqual "6-7"
                }

                context("and player two scores a 2 point lead") {
                    beforeEachTest {
                        game.pointWonBy(Player.TWO)
                    }

                    it("player one wins game") {
                        game.score() shouldEqual "${GameState.WIN_PLAYER_TWO}"
                    }
                }
            }
        }
    }
})
