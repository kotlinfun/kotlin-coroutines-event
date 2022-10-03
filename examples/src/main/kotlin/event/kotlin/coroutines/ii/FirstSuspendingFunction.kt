package event.kotlin.coroutines.ii

import event.kotlin.coroutines.threadName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("${threadName()} Hello")
}

// Your first suspending function!
suspend fun doWorld() {
    delay(2.seconds)
    println("${threadName()} World!")
}