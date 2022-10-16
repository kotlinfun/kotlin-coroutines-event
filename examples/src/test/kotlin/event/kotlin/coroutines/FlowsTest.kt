package event.kotlin.coroutines

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds


@TestInstance(PER_CLASS)
class FlowsTest {


    @Test
    fun `flows are (normally) cold`() = runBlocking {

        val positiveIntegers = flow {
            var element = 1
            while (true) {
                log("Generating element $element")
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
                log("New subscriber: $this")
            }
        }

        val sum = async(start = CoroutineStart.UNDISPATCHED) { hotIntegers.take(5).fold(0, Int::plus) }

        hotIntegers.emit(1)
        hotIntegers.emit(2)
        hotIntegers.emit(3)

        log("Emitted the first 3 elements")

        val product = async(start = CoroutineStart.UNDISPATCHED) { hotIntegers.take(2).fold(1, Int::times) }

        hotIntegers.emit(4)
        hotIntegers.emit(5)

        log("Emitted the last 2 elements")

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

        log("Sum of the first 10 integers: $firstTenIntegersSum")
        log("Even integers in 1..30: $evenIntegersLessThanOrEqualTo30")
        log("Square of the first 5 integers: $first5SquaredIntegers")
    }

    @Test
    fun `flows processing throws an uncaught exception`() = runBlocking {

        flowOf(1, 2, 3, 4)
            .onEach { value ->
                check(value != 2) { "Collected $value" }
                println(value)
            }
            .catch { e -> println("Caught $e") }
            .collect()

    }

    @Test
    fun `flows custom exception handling while processing`() = runBlocking {

        flowOf(1, 2, 3, 4)
            .onEach { value ->
                try {
                    check(value != 2) { "Collected $value" }
                    println(value)
                } catch (e: Throwable) {
                    log("Error message=${e.message}")
                }
            }
            .catch { e -> println("Caught $e") }
            .collect()

    }

    @Test
    fun `cancel flow subscription`() = runBlocking {

        val job = launch {
            flowOf(1, 2, 3, 4)
                .onEach { value ->
                    delay(1000)
                    println(value)
                }
                .catch { e -> println("Caught $e") }
                .collect()
        }
        delay(3000)
        job.cancelAndJoin()
    }


}

private fun Int.power(exponent: Int): Int = toDouble().pow(exponent.toDouble()).toInt()