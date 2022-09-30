package event.kotlin.coroutines.ii

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// Your first suspending function!
suspend fun doWorld() {
    delay(2.seconds)
    println("World!")
}