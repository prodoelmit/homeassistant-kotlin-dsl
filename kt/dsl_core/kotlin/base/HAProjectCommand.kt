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

    private val inputBooleansPath by option("--${FileOption.INPUT_BOOLEANS.argName}", help = "Path for input booleans file")
        .path()
        .default(Path.of(FileOption.INPUT_BOOLEANS.defaultPath))

    override fun run() {
        val fileLocations = FileLocations().apply {
            set(FileOption.AUTOMATIONS, automationsPath)
            set(FileOption.INPUT_BOOLEANS, inputBooleansPath)
        }

        HAProject(fileLocations).apply {
            init
        }.write()
    }
}
