package dsl_core.base

import java.nio.file.Path

class HAAutomationsFile(relativePath: Path, val automations: List<Automation>) : HAFile(relativePath) {
    override fun render(): String = toYaml(automations)
}