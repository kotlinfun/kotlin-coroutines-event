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

suspend fun parallelWorlds() = coroutineScope {

    val job1 = launch(Dispatchers.IO) {
        delay(2.seconds)
        log("World 2")
    }
    val job2 = launch(Dispatchers.IO) {
        delay(3.seconds)
        log("World 1")
    }
    val job3 = launch(Dispatchers.IO) {
        delay(1.seconds)
        log("World 3")
    }

    println("Waiting for all")
    joinAll(job1, job2, job3)
    println("All are done")
}
