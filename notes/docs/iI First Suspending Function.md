
### First Suspending Function Notes

 - the `suspend` modifier indicates a function needs to be run inside a coroutine
 - using Dispatchers.IO to ensure different threads used in runBlocking & launch coroutines
 - note the IDE indicator of a suspend function being called
 - delay is a suspension point
