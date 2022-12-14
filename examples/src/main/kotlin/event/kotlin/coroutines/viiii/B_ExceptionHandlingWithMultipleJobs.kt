package event.kotlin.coroutines.viiii

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.seconds


suspend fun main() {

    try {
        faultyFunction()
    } catch (e: ArithmeticException) {
        println("Oops")
    }

    println("Final")
}

private suspend fun faultyFunction() = supervisorScope {

    println("Something going boom")
    launch {
        delay(1.seconds)
        throw ArithmeticException()
    }
    launch {
        delay(2.seconds)
        println("Running something else")
    }
    delay(2.seconds)
    println("Parent coroutine ..")
}


