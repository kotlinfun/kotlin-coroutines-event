package event.kotlin.coroutines.i

import event.kotlin.coroutines.threadName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(2.seconds) // non-blocking delay for 2 seconds
        println("${threadName()}World!") // print after delay
    }
    println("${threadName()} Hello") // main coroutine continues while a previous one is delayed
}