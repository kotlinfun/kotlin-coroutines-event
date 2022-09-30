package event.kotlin.coroutines.vi

import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.seconds

const val notAsManyThreads = 10_000

fun main() {
    repeat(notAsManyThreads) {
        thread {
            Thread.sleep(5.seconds.inWholeMilliseconds)
            print(".")
        }
    }
}