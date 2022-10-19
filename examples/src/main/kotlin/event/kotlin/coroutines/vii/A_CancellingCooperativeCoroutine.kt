package event.kotlin.coroutines.vii

import event.kotlin.coroutines.log
import event.kotlin.coroutines.threadName
import kotlinx.coroutines.*


fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {//notice runs on different thread from code below
        repeat(1000) { i ->
            log("job: I'm sleeping $i ...")
            delay(500L) //internally performs an is coroutine active check
        }
    }
    delay(1300L) // delay a bit
    log("I'm tired of waiting!")
    job.cancelAndJoin() //show this
    log("Now I can quit.")
}