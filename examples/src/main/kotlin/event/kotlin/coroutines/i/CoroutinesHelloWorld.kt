package event.kotlin.coroutines.i

import event.kotlin.coroutines.log
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope

    launch { // launch a new coroutine and continue
        delay(2.seconds) // non-blocking delay for 2 seconds
        log("World!") // print after delay
    }
    log("Hello") // main coroutine continues while a previous one is delayed
}