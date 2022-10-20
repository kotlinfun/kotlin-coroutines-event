package event.kotlin.coroutines.ii

import event.kotlin.coroutines.log
import event.kotlin.coroutines.threadName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking { // this: CoroutineScope
    launch(Dispatchers.IO) { doWorld() }
    log("Hello")
}

// Your first suspending function!
suspend fun doWorld() { //suspend modifier required for function to run inside a coroutine
    delay(2.seconds)
    log("World!")
}

// What if we want extract to separate function
// note the IDE indicator of a suspend function being called