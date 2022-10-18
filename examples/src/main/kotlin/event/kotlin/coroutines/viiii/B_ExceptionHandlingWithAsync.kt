package event.kotlin.coroutines.viiii

import kotlinx.coroutines.*

val handler = CoroutineExceptionHandler { _, exception -> // coroutineContext,
    println("Caught it $exception")
}

private val scope = CoroutineScope(handler)

suspend fun main() {

    scope.launch {
        faultyFunction()
    }.join()

    println("Final")
}

private suspend fun faultyFunction() {

    println("Something going boom")
    throw ArithmeticException()
}