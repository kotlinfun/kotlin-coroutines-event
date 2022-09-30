dependencies {
    with(Dependencies) {
        api(platform(kotlinCoroutinesBOM()))
        api(coroutinesCore())
        api(coroutinesReactive())
    }
}