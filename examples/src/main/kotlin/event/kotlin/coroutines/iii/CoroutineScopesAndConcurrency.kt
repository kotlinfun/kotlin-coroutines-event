package event.kotlin.coroutines.iii

import event.kotlin.coroutines.log
import event.kotlin.coroutines.threadName
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    launchHelloWorlds()
    log("Done")
}

// Concurrently executes both launched coroutines
suspend fun launchHelloWorlds() = coroutineScope { // this: CoroutineScope
    launch(Dispatchers.IO) {
        delay(2.seconds)
        log("World 2")
    }
    launch(Dispatchers.IO) {
        delay(1.seconds)
        log("World 1")
    }
    log("Hello")
}

//in order spin other coroutines we to be run in a coroutine scope