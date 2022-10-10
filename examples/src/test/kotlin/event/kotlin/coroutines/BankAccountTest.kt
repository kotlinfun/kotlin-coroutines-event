@file:OptIn(ExperimentalCoroutinesApi::class)

package event.kotlin.coroutines

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineStart.UNDISPATCHED
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

class BankAccountTest {

    @Test
    fun increaseBalanceTest() = runTest { //runTest same as runBlocking but skips delays
        val testBankAccount = BankAccount()
        val expectedBalance = 110

        testBankAccount.increaseBalance(10)

        assertThat(testBankAccount.balance).isEqualTo(expectedBalance)
    }

    @Test
    fun increaseBalanceBlockingTest() = runBlocking {
        val testBankAccount = BankAccount()
        val expectedBalance = 110

        testBankAccount.increaseBalance(10)

        assertThat(testBankAccount.balance).isEqualTo(expectedBalance)
    }

    @Nested
    inner class Flows {

        @Test
        fun `flows are (normally) cold`() = runBlocking {

            val positiveIntegers = flow {
                var element = 1
                while (true) {
                    println("Generating element $element")
                    delay(1.seconds)
                    emit(element++)
                }
            }

            val twoToFour = positiveIntegers.drop(1).take(4).toList()
            val twoToFourAgain = positiveIntegers.drop(1).take(4).toList()

            assertThat(twoToFour).isEqualTo((2..5).toList())
            assertThat(twoToFourAgain).isEqualTo((2..5).toList())
        }

        @Test
        fun `flows can be hot as well`() = runBlocking {

            val hotIntegers = MutableSharedFlow<Int>().apply {
                onSubscription {
                    println("New subscriber: $this")
                }
            }

            val sum = async(start = UNDISPATCHED) { hotIntegers.take(5).fold(0, Int::plus) }

            hotIntegers.emit(1)
            hotIntegers.emit(2)
            hotIntegers.emit(3)

            println("Emitted the first 3 elements")

            val product = async(start = UNDISPATCHED) { hotIntegers.take(2).fold(1, Int::times) }

            hotIntegers.emit(4)
            hotIntegers.emit(5)

            println("Emitted the last 2 elements")

            joinAll(sum, product)

            assertThat(sum.await()).isEqualTo(1 + 2 + 3 + 4 + 5)
            assertThat(product.await()).isEqualTo(4 * 5)
        }

        @Test
        fun `flows operations`() = runBlocking {

            val positiveIntegers = generateSequence(1) { i -> i + 1 }.asFlow()

            val firstTenIntegersSum = positiveIntegers.take(10).reduce(Int::plus)
            val evenIntegersLessThanOrEqualTo30 = positiveIntegers.take(30).filter { it % 2 == 0 }.toSet()
            val first5SquaredIntegers = positiveIntegers.map { it.power(2) }.onEach { delay(1.seconds) }.take(5).toList()

            println("Sum of the first 10 integers: $firstTenIntegersSum")
            println("Even integers in 1..30: $evenIntegersLessThanOrEqualTo30")
            println("Square of the first 5 integers: $first5SquaredIntegers")
        }
    }
}

//cancellation and catch on Error

private fun Int.power(exponent: Int): Int = toDouble().pow(exponent.toDouble()).toInt()

class BankAccount {

    var balance: Int = 100

    suspend fun increaseBalance(amount: Int) {
        delay(1000)
        balance += amount
    }



}