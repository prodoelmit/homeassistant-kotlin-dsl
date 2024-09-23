package dsl_core.base

import kt.dsl_core.kotlin.entities.InputBooleanEntity
import java.nio.file.Path
import kotlin.io.path.Path

enum class FileOption(val argName: String, val defaultPath: String) {
    AUTOMATIONS("automations", "automations.yaml"),
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

    fun write() {
        HAAutomationsFile(fileLocations.get(FileOption.AUTOMATIONS), automations).write()
        HAInputBooleansFile(fileLocations.get(FileOption.INPUT_BOOLEANS), inputBooleans).write()
        println("Files written successfully")
    }
}

fun project(fileLocations: FileLocations, init: HAProject.() -> Unit): HAProject {
    return HAProject(fileLocations).apply(init)
}
