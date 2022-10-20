package event.kotlin.coroutines.viiii

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds


suspend fun main() {

    faultyFunction()
    println("Final")
}

private suspend fun faultyFunction() = supervisorScope<Unit> {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    println("Something going boom")
    launch(handler) {
        delay(1.seconds)
        throw ArithmeticException()
    }
    launch {
        delay(2.seconds)
        println("Running something else")
    }
}

