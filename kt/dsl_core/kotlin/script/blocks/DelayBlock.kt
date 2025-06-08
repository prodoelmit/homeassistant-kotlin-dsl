package kt.dsl_core.kotlin.script.blocks

import dsl_core.base.HADuration
import dsl_core.base.ScriptBlocksConsumer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DelayBlock: HAScriptBlock {
    var delay: HADuration = HADuration()

    @SerialName("continue_on_error")
    var continueOnError: Boolean = true
}

fun ScriptBlocksConsumer.delay(init: HADuration.() -> Unit) {
    block(
        DelayBlock().apply {
            delay = HADuration().apply(init)
            continueOnError = true
        }
    )
}