package dsl_core.base

import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.writeText

abstract class HAFile(
    val absolutePath: Path
) {
    final fun write() {
        absolutePath.parent.createDirectories()
        absolutePath.writeText(render())
        println("Writing ${absolutePath}")
    }

    abstract fun render(): String
}