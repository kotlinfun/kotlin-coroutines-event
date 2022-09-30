import com.palantir.gradle.gitversion.GitVersionPlugin
import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.Instant

buildscript {
    repositories { RepositoryConfiguration.BuildScript.apply(this) }
}

plugins { Plugins.Dependencies.addTo(this) }

apply<GitVersionPlugin>()

val parentProject = this
val currentVersion: String by project
val buildTimestamp: Instant = Instant.now()
val versionDetails: Closure<VersionDetails> by extra
val gitVersion = versionDetails()

allprojects {
    group = ProjectSettings.groupId
    version = currentVersion

    version = currentVersion

    repositories { RepositoryConfiguration.Modules.apply(this) }

    apply<IdeaPlugin>()
    idea {
        module {
            inheritOutputDirs = true
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.apply {
            jvmTarget = KotlinConfiguration.TargetJvm.version
            javaParameters = KotlinConfiguration.Compiler.generateJavaParameters
            freeCompilerArgs = KotlinConfiguration.Compiler.arguments
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        maxParallelForks = Runtime.getRuntime().availableProcessors() * 2
        testLogging {
            showStandardStreams = false
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin<JavaLibraryPlugin>()
    }

    configure<JavaPluginExtension> { Plugins.JavaPlugin.configure(this) }
}