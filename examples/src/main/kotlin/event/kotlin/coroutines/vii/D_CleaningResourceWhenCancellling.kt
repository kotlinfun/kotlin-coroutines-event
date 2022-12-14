package event.kotlin.coroutines.vii

import event.kotlin.coroutines.log
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        try {
            repeat(1000) { i ->
                log("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            cleanUpResources()
        }
    }
    delay(1300L) // delay a bit
    log("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    log("main: Now I can quit.")
}


private suspend fun cleanUpResources() {

    withContext(NonCancellable) { // this is needed because you cannot normally suspend after a coroutine has been cancelled
        delay(1.seconds)
        log("Cleaned up resources")
    }
}


//can be appended to an existing job
//private suspend fun main() = coroutineScope {
//
//    val job = launch {
//        repeat(1000) { i ->
//            log("job: I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//        val handle = job.invokeOnCompletion { error ->
//        if (error is CancellationException) {
//            runBlocking { cleanUpResources() }
//        }
//    }
//
//    delay(1300L) // delay a bit
//    log("main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    log("main: Now I can quit.")
//}

