package event.kotlin.coroutines.vi

import event.kotlin.coroutines.threadName
import kotlin.concurrent.thread
import kotlin.time.Duration.Companion.seconds

const val notAsManyThreads = 10_000

fun main() { // Look for `Failed to start the native thread for java.lang.Thread "Thread-8159"`
    repeat(notAsManyThreads) {
        thread {
            Thread.sleep(5.seconds.inWholeMilliseconds)
            print("${threadName()}.")
        }
    }
}