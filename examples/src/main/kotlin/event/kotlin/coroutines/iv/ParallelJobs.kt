package event.kotlin.coroutines.iv

import event.kotlin.coroutines.log
import event.kotlin.coroutines.threadName
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    log("Hello")
    parallelWorlds()
    log("Done")
}

// Concurrently executes both launched coroutines
suspend fun parallelWorlds() = coroutineScope { // this: CoroutineScope

    val job1 = launch {
        delay(2.seconds)
        log("World 2")
    }
    val job2 = launch {
        delay(3.seconds)
        log("World 1")
    }
    val job3 = launch {
        delay(1.seconds)
        log("World 3")
    }

    joinAll(job1, job2, job3)
}