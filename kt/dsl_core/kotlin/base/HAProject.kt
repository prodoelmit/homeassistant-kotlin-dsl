package dsl_core.base

import java.nio.file.Path
import kotlin.io.path.Path

class HAProject {
    val automations = mutableListOf<Automation>()
    lateinit var baseDirectory: Path

    fun automation(init: Automation.() -> Unit) {
        Automation().apply(init).apply {
            automations.add(this)
        }
    }

    fun automation(automation: Automation) {
        automations.add(automation)
    }

    fun write() {
        HAAutomationsFile(Path("dsl_automations.yaml"), automations).write(baseDirectory)
    }
}

fun project(init: HAProject.() -> Unit) {
    HAProject().apply(init).write()
}
