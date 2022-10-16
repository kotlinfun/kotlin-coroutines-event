package event.kotlin.coroutines

fun threadName() = "[${Thread.currentThread().name}]"

fun log(message : String) = println("${threadName()} $message")
fun log(message : Int) = println("${threadName()} $message")
