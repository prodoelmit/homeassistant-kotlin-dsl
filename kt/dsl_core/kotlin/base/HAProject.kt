package dsl_core.base

import dsl_core.base.files.HAAutomationsFile
import dsl_core.base.files.HACountersFile
import dsl_core.base.files.HAInputBooleansFile
import kt.dsl_core.kotlin.entities.CounterEntity
import kt.dsl_core.kotlin.entities.InputBooleanEntity
import java.nio.file.Path
import kotlin.io.path.Path

enum class FileOption(val argName: String, val defaultPath: String) {
    AUTOMATIONS("automations", "automations.yaml"),
    COUNTERS("counters", "counters.yaml"),
    INPUT_BOOLEANS("input-booleans", "input_booleans.yaml");
}

class FileLocations {
    private val locations = mutableMapOf<FileOption, Path>()

    fun get(option: FileOption): Path = locations.getOrDefault(option, Path.of(option.defaultPath))

    fun set(option: FileOption, path: Path) {
        locations[option] = path
    }
}

class HAProject(private val fileLocations: FileLocations) {
    val automations = mutableListOf<Automation>()
    val inputBooleans = mutableListOf<InputBooleanEntity>()
    val counters = mutableMapOf<String, CounterEntity>()

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

    fun write() {
        HAAutomationsFile(fileLocations.get(FileOption.AUTOMATIONS), automations).write()
        HAInputBooleansFile(fileLocations.get(FileOption.INPUT_BOOLEANS), inputBooleans).write()
        HACountersFile(fileLocations.get(FileOption.COUNTERS), counters).write()
        println("Files written successfully")
    }
}

fun project(fileLocations: FileLocations, init: HAProject.() -> Unit): HAProject {
    return HAProject(fileLocations).apply(init)
}
