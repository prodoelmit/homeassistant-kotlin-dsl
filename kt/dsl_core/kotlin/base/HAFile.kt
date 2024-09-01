package dsl_core.base

import java.nio.file.Path
import kotlin.io.path.writeText

abstract class HAFile(
    val relativePath: Path
) {
    final fun write(baseDirectory: Path) {
        val path = baseDirectory.resolve(relativePath)
        path.writeText(render())
    }

    abstract fun render(): String
}