import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

object RepositoryConfiguration {

    object BuildScript {

        fun apply(config: RepositoryHandler) {
            config.mavenCentral()
        }
    }

    object Modules {

        fun apply(config: RepositoryHandler) {
            config.mavenCentral()
            config.maven {
                url = URI.create("https://jitpack.io")
            }
            config.mavenLocal()
        }
    }
}