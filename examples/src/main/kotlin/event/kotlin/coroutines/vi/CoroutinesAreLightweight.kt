package event.kotlin.coroutines.vi

import event.kotlin.coroutines.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

const val aBoatLoadOfCoroutines = 1_000_000

suspend fun main() = coroutineScope {
    repeat(aBoatLoadOfCoroutines) { // launch 1M coroutines!
        launch {
            delay(5.seconds) //all coroutines delayed for 5 seconds
            log(".")
        }
    }
}
