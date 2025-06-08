package kt.dsl_core.kotlin.script.blocks

import dsl_core.base.Entity
import dsl_core.base.ScriptBlocksConsumer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.actions.ActionNames

@Serializable
class RemoteSendBlock : HAScriptBlock {
    var action: String = ""
    var target: Target = Target()
    var data: MutableMap<String, String> = mutableMapOf()

    @SerialName("continue_on_error")
    var continueOnError: Boolean = true

    init {
        action = ActionNames.Remote.SendCommand
    }

    @Serializable
    class Target(
        @SerialName("entity_id")
        var entityIds: MutableList<String> = mutableListOf()
    )

    fun entity(entity: Entity) {
        target.entityIds.add(entity.id())
    }

    fun command(value: String) {
        data["command"] = value
    }

    fun device(value: String) {
        data["device"] = value
    }

    fun numRepeats(value: Int) {
        data["num_repeats"] = value.toString()
    }

    fun delaySecs(value: Float) {
        data["delay_secs"] = value.toString()
    }

    fun holdSecs(value: Float) {
        data["hold_secs"] = value.toString()
    }
}

fun ScriptBlocksConsumer.remoteSend(init: RemoteSendBlock.() -> Unit) {
    block(RemoteSendBlock().apply(init))
}