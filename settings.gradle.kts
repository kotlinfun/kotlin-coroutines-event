rootProject.name = "kotlin-coroutines-event"

module("coroutines", "dependencies")
module("test", "dependencies")
module("examples")

fun module(firstPathSegment: String, vararg pathSegments: String) {

    val projectPath = arrayOf(firstPathSegment, *pathSegments)

    require(projectPath.isNotEmpty()) { "Project path must contain at least 1 element." }

    val projectName = projectPath.last()
    val path = projectPath.sliceArray(0 until projectPath.size - 1)
    val group = path.joinToString(separator = "-")
    val directory = path.joinToString(separator = "/", prefix = "./")

    include("${if (group.isEmpty()) "" else "$group-"}$projectName")
    project(":${if (group.isEmpty()) "" else "$group-"}$projectName").projectDir = file("$directory/$projectName")
}

