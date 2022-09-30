object KotlinConfiguration {

    const val version = Versions.Kotlin.value

    object TargetJvm {
        const val version = "17"
    }

    object Compiler {
        val arguments = listOf("-Xopt-in=kotlin.Experimental")
        const val generateJavaParameters = true
    }
}