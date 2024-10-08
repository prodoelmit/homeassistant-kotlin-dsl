package dsl_core.base

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.path
import dsl_core.base.FileLocations
import dsl_core.base.FileOption
import dsl_core.base.HAProject
import java.nio.file.Path

class HAProjectCommand(val init: HAProject.() -> Unit) : CliktCommand() {
    private val automationsPath by option("--${FileOption.AUTOMATIONS.argName}", help = "Path for automations file")
        .path()
        .default(Path.of(FileOption.AUTOMATIONS.defaultPath))

    private val inputBooleansPath by option(
        "--${FileOption.INPUT_BOOLEANS.argName}",
        help = "Path for input booleans file"
    )
        .path()
        .default(Path.of(FileOption.INPUT_BOOLEANS.defaultPath))

    private val countersPath by option(
        "--${FileOption.COUNTERS.argName}",
        help = "Path for counters file"
    )
        .path()
        .default(Path.of(FileOption.COUNTERS.defaultPath))

    private val binarySensorsPath by option(
        "--${FileOption.BINARY_SENSORS.argName}",
        help = "Path for binary sensors file"
    )
        .path()
        .default(Path.of(FileOption.BINARY_SENSORS.defaultPath))

    private val scriptsPath by option(
        "--${FileOption.SCRIPTS.argName}",
        help = "Path for scripts file"
    )
        .path()
        .default(Path.of(FileOption.SCRIPTS.defaultPath))

    private val dashboardPath by option(
        "--${FileOption.DASHBOARD.argName}",
        help = "Path for dashboards dir"
    )
        .path()
        .default(Path.of(FileOption.DASHBOARD.defaultPath))

    override fun run() {
        val fileLocations = FileLocations().apply {
            set(FileOption.AUTOMATIONS, automationsPath)
            set(FileOption.INPUT_BOOLEANS, inputBooleansPath)
            set(FileOption.COUNTERS, countersPath)
            set(FileOption.DASHBOARD, dashboardPath)
            set(FileOption.SCRIPTS, scriptsPath)
            set(FileOption.BINARY_SENSORS, binarySensorsPath)
        }

        HAProject(fileLocations).apply(init).write()
    }
}
