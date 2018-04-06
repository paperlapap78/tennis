package au.com.dius.dennis.kotlin.tennis

import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object CalculatorSpec : Spek({
    given("a calculator") {
        val calculator = Calculator()
        on("addition") {
            val sum = calculator.sum(2, 4)
            it("should return the result of adding the first number to the scond number") {
                sum shouldBe 6
            }
        }
        on("subsraction") {
            val substract = calculator.substract(4, 2)
            it("should return the result of substracting the second number from the first number") {
                substract shouldBe 2
            }
        }
    }
})