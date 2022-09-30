package event.kotlin.coroutines.v

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

suspend fun main() = coroutineScope { // this: CoroutineScope

    val theInt = async { computeInt() }
    val theString = async { computeString() }
    val theBoolean = async { computeBoolean() }

    println("Int: ${theInt.await()}, String: ${theString.await()}, Boolean: ${theBoolean.await()}")
}

private suspend fun computeInt(): Int {

    delay(1.seconds)
    return 3
}

private suspend fun computeString(): String {

    delay(2.seconds)
    return "Hola"
}

private suspend fun computeBoolean(): Boolean = computeInt() % 2 == 0