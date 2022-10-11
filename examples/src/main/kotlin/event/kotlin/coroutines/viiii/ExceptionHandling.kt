package event.kotlin.coroutines.viiii

import event.kotlin.coroutines.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = GlobalScope.launch { // root coroutine with launch
        log("Throwing exception from launch")
        throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    log("Joined failed job")
    val deferred = GlobalScope.async { // root coroutine with async
        log("Throwing exception from async")
        throw ArithmeticException() // Nothing is printed, relying on user to call await
    }
    try {
        deferred.await()
        log("Unreached")
    } catch (e: ArithmeticException) {
        log("Caught ArithmeticException")
    }
}