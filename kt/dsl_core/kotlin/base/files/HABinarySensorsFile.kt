package dsl_core.base.files

import dsl_core.base.HAFile
import dsl_core.base.toYaml
import kt.dsl_core.kotlin.entities.BinarySensorEntity
import kt.dsl_core.kotlin.entities.CounterEntity
import java.nio.file.Path

class HABinarySensorsFile(relativePath: Path, val binarySensors: List<BinarySensorEntity>) : HAFile(relativePath) {
    override fun render(): String = toYaml(binarySensors)
}