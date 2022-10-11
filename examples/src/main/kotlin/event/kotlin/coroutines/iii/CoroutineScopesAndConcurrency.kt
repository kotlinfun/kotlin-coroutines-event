package event.kotlin.coroutines.iii

import event.kotlin.coroutines.log
import event.kotlin.coroutines.threadName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    launchHelloWorlds()
    log("Done")
}

// Concurrently executes both launched coroutines
suspend fun launchHelloWorlds() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2.seconds)
        log("World 2")
    }
    launch {
        delay(1.seconds)
        log("World 1")
    }
    log("Hello")
}