package dsl_core.base

import java.nio.file.Path
import kotlin.io.path.writeText

abstract class HAFile(
    val absolutePath: Path
) {
    final fun write() {
        absolutePath.writeText(render())
    }

    abstract fun render(): String
}