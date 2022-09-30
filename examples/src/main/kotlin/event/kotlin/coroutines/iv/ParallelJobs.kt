package event.kotlin.coroutines.iv

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

fun main() = runBlocking {
    println("Hello")
    parallelWorlds()
    println("Done")
}

// Concurrently executes both launched coroutines
suspend fun parallelWorlds() = coroutineScope { // this: CoroutineScope

    val job1 = launch {
        delay(2.seconds)
        println("World 2")
    }
    val job2 = launch {
        delay(3.seconds)
        println("World 1")
    }
    val job3 = launch {
        delay(1.seconds)
        println("World 3")
    }

    joinAll(job1, job2, job3)
}