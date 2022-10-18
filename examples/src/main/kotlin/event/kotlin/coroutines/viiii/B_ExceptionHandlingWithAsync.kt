package event.kotlin.coroutines.viiii

import event.kotlin.coroutines.log
import kotlinx.coroutines.*

//fun main() = runBlocking {
//
//    val handler = CoroutineExceptionHandler { ctx, throwable ->
//        // nothing
//        println("Fuck")
//    }
//    val deferred = async(handler) { // root coroutine with async
//        log("Throwing exception from async")
//        throw ArithmeticException() // Nothing is printed, relying on user to call await
//    }
//    try {
//        deferred.await() //TODO exception is throw not swallowed
//        log("Unreached")
//    } catch (e: ArithmeticException) {
//        log("Caught ArithmeticException")
//    }
//}

val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("Caught it")
}
private val scope = CoroutineScope(handler)

suspend fun main() {

    try {
        scope.launch {
            fuck()
        }.join()
    } catch (e: ArithmeticException) {
        println("Here goes nothing")
    }
    println("Final")
}

private suspend fun fuck() {

    println("Something going boom")
    throw ArithmeticException()
}