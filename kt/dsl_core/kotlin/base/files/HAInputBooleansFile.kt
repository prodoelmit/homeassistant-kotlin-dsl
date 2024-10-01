package dsl_core.base.files

import dsl_core.base.HAFile
import dsl_core.base.toYaml
import kt.dsl_core.kotlin.entities.InputBooleanEntity
import java.nio.file.Path

class HAInputBooleansFile(relativePath: Path, val inputBooleans: Map<String, InputBooleanEntity>) :
    HAFile(relativePath) {
    override fun render(): String = toYaml(inputBooleans)
}