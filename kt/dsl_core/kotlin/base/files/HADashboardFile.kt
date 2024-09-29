package dsl_core.base.files

import dsl_core.base.HAFile
import dsl_core.base.toYaml
import kt.dsl_core.kotlin.entities.CounterEntity
import kt.dsl_core.kotlin.ui.Dashboard
import java.nio.file.Path

class HADashboardFile(relativePath: Path, val dashboard: Dashboard) : HAFile(relativePath) {
    override fun render(): String = toYaml(dashboard)
}