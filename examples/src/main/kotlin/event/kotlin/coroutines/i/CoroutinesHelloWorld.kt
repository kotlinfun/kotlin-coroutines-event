package event.kotlin.coroutines.i

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(2.seconds) // non-blocking delay for 2 seconds
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}