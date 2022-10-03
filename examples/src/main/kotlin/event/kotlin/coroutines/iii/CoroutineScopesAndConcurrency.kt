package event.kotlin.coroutines.iii

import event.kotlin.coroutines.threadName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    launchHelloWorlds()
    println("${threadName()} Done")
}

// Concurrently executes both launched coroutines
suspend fun launchHelloWorlds() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2.seconds)
        println("${threadName()} World 2")
    }
    launch {
        delay(1.seconds)
        println("${threadName()} World 1")
    }
    println("${threadName()} Hello")
}