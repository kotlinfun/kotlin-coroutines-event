package event.kotlin.coroutines.iii

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    launchHelloWorlds()
    println("Done")
}

// Concurrently executes both launched coroutines
suspend fun launchHelloWorlds() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2.seconds)
        println("World 2")
    }
    launch {
        delay(1.seconds)
        println("World 1")
    }
    println("Hello")
}