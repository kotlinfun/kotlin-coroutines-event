package event.kotlin.coroutines.vii

import event.kotlin.coroutines.log
import kotlinx.coroutines.*


fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        repeat(5) { i ->
            try {
                // print a message twice a second
                log("job: I'm sleeping $i ...")
                delay(500)
            } catch (e: Exception) {
                // log the exception
                log(e.message!!) //prints StandaloneCoroutine was cancelled
            }
        }
    }
    delay(1300L) // delay a bit
    log("I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    log("Now I can quit.")
}