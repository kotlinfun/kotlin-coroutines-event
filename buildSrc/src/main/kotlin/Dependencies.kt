import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency

object Dependencies {
    fun kotlinCoroutinesBOM(version: String = Versions.Kotlin.Coroutines.value) = kotlinx("kotlinx-coroutines-bom", version)
    fun coroutinesCore(version: String = Versions.bomDependency) = coroutines("core", version)
    fun coroutinesTest(version: String = Versions.bomDependency) = coroutines("test", version)
    fun coroutinesReactive(version: String = Versions.bomDependency) = coroutines("reactive", version)

    fun junitJupiterApi(version: String = Versions.JUnit.Jupiter.value) = junitJupiter("junit-jupiter-api", version)
    fun junitJupiterEngine(version: String = Versions.JUnit.Jupiter.value) = junitJupiter("junit-jupiter-engine", version)
    fun junitParams(version: String = Versions.JUnit.Jupiter.value) = junitJupiter("junit-jupiter-params", version)
    fun junitPlatformLauncher(version: String = Versions.JUnit.Platform.value) = dependency("org.junit.platform", "junit-platform-launcher", version)

    fun assertk(version: String = Versions.AssertK.value) = dependency("com.willowtreeapps.assertk", "assertk", version)
}

private fun coroutines(name: String, version: String) = kotlinx("kotlinx-coroutines-$name", version)
private fun kotlinx(name: String, version: String) = dependency("org.jetbrains.kotlinx", name, version)
private fun junitJupiter(name: String, version: String) = dependency("org.junit.jupiter", name, version)
private fun dependency(group: String, name: String, version: String): ModuleDependency = DefaultExternalModuleDependency(group, name, version)