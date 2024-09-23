package dsl_core.base

import kt.dsl_core.kotlin.entities.InputBooleanEntity
import java.nio.file.Path

class HAInputBooleansFile(relativePath: Path, val inputBooleans: List<InputBooleanEntity>) : HAFile(relativePath) {
    override fun render(): String = toYaml(inputBooleans)
}