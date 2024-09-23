package dsl_core.base

import java.nio.file.Path

class HAAutomationsFile(absolutePath: Path, val automations: List<Automation>) : HAFile(absolutePath) {
    override fun render(): String = toYaml(automations)
}