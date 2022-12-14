package event.kotlin.coroutines.vii

import event.kotlin.coroutines.log
import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

fun main() = runBlocking {

    val startTime = currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (currentTimeMillis() >= nextPrintTime) {
                log("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    log(" I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    log(" Now I can quit.")
}



