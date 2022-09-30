dependencies {
    with(Dependencies) {
        api(kotlin("test"))
        api(platform(kotlinCoroutinesBOM()))
        api(coroutinesTest())
        api(junitJupiterApi())
        api(junitParams())
        api(assertk())

        runtimeOnly(junitJupiterEngine())
    }
}