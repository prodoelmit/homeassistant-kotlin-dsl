package dsl_core.base.files

import dsl_core.base.HAFile
import dsl_core.base.toYaml
import kt.dsl_core.kotlin.entities.CounterEntity
import java.nio.file.Path

class HACountersFile(relativePath: Path, val counters: Map<String, CounterEntity>) : HAFile(relativePath) {
    override fun render(): String = toYaml(counters)
}