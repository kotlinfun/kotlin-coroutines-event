package event.kotlin.coroutines.v

import event.kotlin.coroutines.log
import kotlinx.coroutines.*
import kotlin.system.*

suspend fun main() = coroutineScope {

    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // some computation
        delay(1000)
        log("Now starting one")
        one.start() // start the first one
        delay(1000)
        log("Now starting two")
        two.start() // start the second one
        log("The answer is ${one.await() + two.await()}")
    }
    log("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    log("doSomethingUsefulOne")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    log("doSomethingUsefulTwo")
    return 29
}