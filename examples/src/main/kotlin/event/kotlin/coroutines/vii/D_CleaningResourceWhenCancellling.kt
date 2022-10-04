package event.kotlin.coroutines.vii

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            cleanUpResources()
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

private suspend fun cleanUpResources() {

    withContext(NonCancellable) { // this is needed because you cannot normally suspend after a coroutine has been cancelled
        delay(1.seconds)
        println("Cleaned up resources")
    }
}