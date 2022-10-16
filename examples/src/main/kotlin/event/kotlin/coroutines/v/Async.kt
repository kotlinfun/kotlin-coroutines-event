package event.kotlin.coroutines.v

import event.kotlin.coroutines.log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

suspend fun main() = coroutineScope { // this: CoroutineScope (This function is designed for parallel decomposition of work)

    val theInt = async { computeInt() } //async returns Deferred Coroutine
    val theString = async { computeString() }
    val theBoolean = async { computeBoolean() }

    log("Int: ${theInt.await()}, String: ${theString.await()}, Boolean: ${theBoolean.await()}") //will wait for all before printing out
}

private suspend fun computeInt(): Int {

    log("computeInt")
    delay(1.seconds)
    return 3
}

private suspend fun computeString(): String {

    log("computeInt")
    delay(2.seconds)
    return "Hola"
}

private suspend fun computeBoolean(): Boolean = computeInt() % 2 == 0