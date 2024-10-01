package dsl_core.base.files

import dsl_core.base.HAFile
import dsl_core.base.HAScript
import dsl_core.base.toYaml
import kt.dsl_core.kotlin.entities.CounterEntity
import java.nio.file.Path

class HAScriptsFile(relativePath: Path, val scripts: Map<String, HAScript>) : HAFile(relativePath) {
    override fun render(): String = toYaml(scripts)
}