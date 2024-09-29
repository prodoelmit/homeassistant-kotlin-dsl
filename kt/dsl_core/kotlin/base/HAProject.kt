package dsl_core.base

import dsl_core.base.files.HAAutomationsFile
import dsl_core.base.files.HACountersFile
import dsl_core.base.files.HADashboardFile
import dsl_core.base.files.HAInputBooleansFile
import kt.dsl_core.kotlin.entities.CounterEntity
import kt.dsl_core.kotlin.entities.InputBooleanEntity
import kt.dsl_core.kotlin.ui.Dashboard
import java.nio.file.Path
import kotlin.io.path.Path

enum class FileOption(val argName: String, val defaultPath: String) {
    AUTOMATIONS("automations", "automations.yaml"),
    COUNTERS("counters", "counters.yaml"),
    INPUT_BOOLEANS("input-booleans", "input_booleans.yaml"),
    DASHBOARD("dashboard", "dashboard")
}

class FileLocations {
    private val locations = mutableMapOf<FileOption, Path>()

    fun get(option: FileOption): Path = locations.getOrDefault(option, Path.of(option.defaultPath))

    fun get(dashboard: Dashboard): Path = locations
            .getOrDefault(FileOption.DASHBOARD, Path.of(FileOption.DASHBOARD.defaultPath).resolve("qwe")).parent
            .resolve("${dashboard.path}.yaml")

    fun set(option: FileOption, path: Path) {
        locations[option] = path
    }
}

class HAProject(private val fileLocations: FileLocations) {
    val automations = mutableListOf<Automation>()
    val inputBooleans = mutableListOf<InputBooleanEntity>()
    val counters = mutableMapOf<String, CounterEntity>()
    val dashboards = mutableMapOf<String, Dashboard>()

    fun automation(init: Automation.() -> Unit) {
        automations.add(Automation().apply(init))
    }

    fun automation(existingAutomation: Automation) {
        automations.add(existingAutomation)
    }

    fun inputBoolean(init: InputBooleanEntity.() -> Unit) {
        inputBooleans.add(InputBooleanEntity().apply(init))
    }

    fun inputBoolean(existingInputBoolean: InputBooleanEntity) {
        inputBooleans.add(existingInputBoolean)
    }

    fun counter(existingCounter: CounterEntity) {
        counters[existingCounter.alias] = existingCounter
    }

    fun dashboard(existingDashboard: Dashboard) {
        dashboards[existingDashboard.path] = existingDashboard
    }


    fun write() {
        HAAutomationsFile(fileLocations.get(FileOption.AUTOMATIONS), automations).write()
        HAInputBooleansFile(fileLocations.get(FileOption.INPUT_BOOLEANS), inputBooleans).write()
        HACountersFile(fileLocations.get(FileOption.COUNTERS), counters).write()
        dashboards.forEach { dashboardName, dashboard ->
            println("writing dashboard ${dashboardName}")
            HADashboardFile(fileLocations.get(dashboard), dashboard).write()
        }
        println("Files written successfully")
    }
}

fun project(fileLocations: FileLocations, init: HAProject.() -> Unit): HAProject {
    return HAProject(fileLocations).apply(init)
}
