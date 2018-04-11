package dius.dennis.tennis

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object GameSpec: Spek({

    describe("a tennis game with two players") {
        val game = Game()

        afterEachTest {
            game.clear()
        }

        context("player one scores") {
            beforeEachTest {
                game.pointWonBy(Player.ONE)
            }

            it("shows player one in the lead with 15-0") {
                game.score() shouldEqual "15-0"
            }

            context("and player two scores") {
                beforeEachTest {
                    game.pointWonBy(Player.TWO)
                }

                it("shows player one and two equal with 15-15") {
                    game.score() shouldEqual "15-15"
                }
            }
        }

        context("player two scores") {
            beforeEachTest {
                game.pointWonBy(Player.TWO)
            }

            it("shows player two in the lead with 0-15") {
                game.score() shouldEqual "0-15"
            }
        }

        context("player one has 4 points") {
            beforeEachTest {
                for(i in 1..4) game.pointWonBy(Player.ONE)
            }

            context("and 4 points more than opponent") {
                it("player one wins game") {
                    game.score() shouldEqual "${GameState.WIN_PLAYER_ONE}"
                }
            }

            context("and 2 points more than opponent") {
                beforeEachTest {
                    for(i in 1..2) game.pointWonBy(Player.TWO)
                }

                it("player one wins game") {
                    game.score() shouldEqual "${GameState.WIN_PLAYER_ONE}"
                }

            }

            context("and only 1 point more than opponent") {
                beforeEachTest {
                    for(i in 1..3) game.pointWonBy(Player.TWO)
                }

                it("game advantage player one") {
                    game.score() shouldEqual "${GameState.ADVANTAGE_PLAYER_ONE}"
                }
            }
        }

        context("player two has 4 points") {
            beforeEachTest {
                for (i in 1..4) game.pointWonBy(Player.TWO)
            }

            context("and 4 points more than opponent") {

                it("player two wins game") {
                    game.score() shouldEqual "${GameState.WIN_PLAYER_TWO}"
                }
            }

            context("and only 1 point more than opponent") {
                beforeEachTest {
                    for(i in 1..3) game.pointWonBy(Player.ONE)
                }

                it("game advantage by player two") {
                    game.score() shouldEqual "${GameState.ADVANTAGE_PLAYER_TWO}"
                }
            }

        }

        context("both have equal score") {

            context( "2 points each") {
                beforeEachTest {
                    for(i in 1..2) {
                        game.pointWonBy(Player.ONE)
                        game.pointWonBy(Player.TWO)
                    }
                }

                it("game shows equal game score") {
                    game.score() shouldEqual "30-30"
                }
            }

            context("3 points each") {

                beforeEachTest {
                    for(i in 1..3) {
                        game.pointWonBy(Player.ONE)
                        game.pointWonBy(Player.TWO)
                    }
                }

                it("game is deuce") {
                    game.score() shouldEqual "${GameState.DEUCE}"
                }
            }

            context( "4 points each") {

                beforeEachTest {
                    for(i in 1..4) {
                        game.pointWonBy(Player.ONE)
                        game.pointWonBy(Player.TWO)
                    }
                }

                it("game is deuce") {
                    game.score() shouldEqual "${GameState.DEUCE}"
                }
            }
        }
    }
})



