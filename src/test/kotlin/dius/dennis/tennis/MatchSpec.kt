package dius.dennis.tennis

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xit

object MatchSetSpec: Spek({

    describe("a tennis match with a matchSet of games and two players") {
        val match = MatchSet()

        afterEachTest {
            match.clear()
        }

        context("player one makes first score") {
            beforeEachTest {
                match.pointWonBy(Player.ONE)
            }

            it("shows player one leading first game") {
                match.score() shouldEqual "0-0, 15-0"
            }
        }

        context("player one won 1 game") {
            beforeEachTest {
                for(i in 1..4) match.pointWonBy(Player.ONE)
            }

            context("and scores 1 point") {
                beforeEachTest {
                    match.pointWonBy(Player.ONE)
                }

                it("player leads game 2 with 15-0") {
                    match.score() shouldEqual "1-0, 15-0"
                }
            }
        }

        context("player one won 4 games") {
            beforeEachTest {
                for (i in 1..16) match.pointWonBy(Player.ONE)
            }

            context("and player two won 6 games") {
                beforeEachTest {
                    for (i in 1..24) match.pointWonBy(Player.TWO)
                }

                it("player two wins match") {
                    match.score() shouldEqual "4-6, ${SetState.WIN_PLAYER_TWO}"
                }
            }
        }

        context("player one won 5 games") {
            beforeEachTest {
                for(i in 1..20) match.pointWonBy(Player.ONE)
            }

            context("and player two won 6 games") {
                beforeEachTest {
                    for (i in 1..24) match.pointWonBy(Player.TWO)
                }

                it("an additional game is played") {
                    match.score() shouldEqual "5-6, 0-0"
                }

                context("and player two wins that game") {
                    beforeEachTest {
                        for (i in 1..4) match.pointWonBy(Player.TWO)
                    }

                    it("player two wins match set") {
                        match.score() shouldEqual "5-7, ${SetState.WIN_PLAYER_TWO}"
                    }
                }

                context("and player one wins that game") {
                    beforeEachTest {
                        for (i in 1..4) match.pointWonBy(Player.ONE)
                    }

                    it("match goes into tie break") {
                        match.score() shouldEqual "6-6, 0-0"
                    }

                }
            }
        }

        context("player two won 6 games") {
            beforeEachTest {
                for(i in 1..24) match.pointWonBy(Player.TWO)
            }

            context("and 2 games more than opponent") {
                beforeEachTest {
                    for(i in 1..16) match.pointWonBy(Player.ONE)
                }

                xit("player two wins match") {
                    match.score() shouldEqual "4-6, ${SetState.WIN_PLAYER_TWO}"
                }
            }
        }

        context("tie with 6 games each") {
            beforeEachTest {
                for(i in 1..20) match.pointWonBy(Player.ONE)
                for(i in 1..24) match.pointWonBy(Player.TWO)
                for(i in 1..4) match.pointWonBy(Player.ONE)
            }

            it("match goes into tie-break") {
                match.score() shouldEqual "6-6, 0-0"
            }

            context("and player one scores in tie break") {
                beforeEachTest {
                    match.pointWonBy(Player.ONE)
                }

                it("player one leads tie-break 1-0") {
                    match.score() shouldEqual "6-6, 1-0"
                }
            }

            context("and player one wins tie-break") {
                beforeEachTest {
                    for(i in 1..7) match.pointWonBy(Player.ONE)
                }

                it("player one wins match set") {
                    match.score() shouldEqual "7-6, ${SetState.WIN_PLAYER_ONE}"
                }
            }

            context("and player two wins tie-break") {
                beforeEachTest {
                    for(i in 1..7) match.pointWonBy(Player.TWO)
                }

                it("player two wins match set") {
                    match.score() shouldEqual "6-7, ${SetState.WIN_PLAYER_TWO}"
                }
            }
        }
    }
})
