package dsl_core.base.files

import dsl_core.base.Automation
import dsl_core.base.HAFile
import dsl_core.base.toYaml
import java.nio.file.Path

class HAAutomationsFile(absolutePath: Path, val automations: List<Automation>) : HAFile(absolutePath) {
    override fun render(): String = toYaml(automations)
}