package event.kotlin.coroutines.viiii

import event.kotlin.coroutines.log
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val deferred = async { // root coroutine with async
        log("Throwing exception from async")
        throw ArithmeticException() // Nothing is printed, relying on user to call await
    }
    try {
        deferred.await() //TODO exception is throw not swallowed
        log("Unreached")
    } catch (e: ArithmeticException) {
        log("Caught ArithmeticException")
    }
}