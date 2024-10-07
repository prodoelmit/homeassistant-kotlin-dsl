package kt.dsl_core.kotlin.script.blocks

import dsl_core.base.HADuration
import dsl_core.base.ScriptBlocksConsumer
import kotlinx.serialization.Serializable

@Serializable
class DelayBlock: HAScriptBlock {
    var delay: HADuration = HADuration()
}

fun ScriptBlocksConsumer.delay(init: HADuration.() -> Unit) {
    block(
        DelayBlock().apply {
            delay = HADuration().apply(init)
        }
    )
}