
### Cancelling  Coroutine

#### Cooperative

 - Using the `Job` return by the launch coroutine builder to cancel cooperative coroutine
 - cooperative coroutine is where the isActive flag, this is done by the delay method

#### Un Cooperative

 - Swallowing CancellationException makes the coroutine cooperative  

#### Computation Cooperative

 - checks the isActive property of the coroutine

#### Cleaning Resources

 - within  try/finally clean up resources using withContext(NonCancellable) 