package kt.dsl_core.kotlin.script.blocks

import dsl_core.base.Entity
import dsl_core.base.HAScriptBlocks
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
open class CallActionBlock: HAScriptBlock {
    var action: String = ""
    var target: Target = Target()
    var data: MutableMap<String, String> =  mutableMapOf()

    @Serializable
    class Target(
        @SerialName("entity_id")
        var entityIds: MutableList<String> = mutableListOf()
    )

    fun entity(entity: Entity) {
        target.entityIds.add(entity.id())
    }

}

@Serializable
class CallMediaActionBlock(): HAScriptBlock {
    var action: String = ""
    var target: Target = Target()
    var data: MutableMap<String, String> =  mutableMapOf()

    @Serializable
    class Target(
        @SerialName("entity_id")
        var entityIds: MutableList<String> = mutableListOf()
    )

    fun entity(entity: Entity) {
        target.entityIds.add(entity.id())
    }
    fun mediaContentId(value: String) {
        data["media_content_id"] = value
    }

    fun mediaContentType(value: String) {
        data["media_content_type"] = value
    }
}

fun HAScriptBlocks.callAction(init: CallActionBlock.() -> Unit) {
    block(
        CallActionBlock().apply(init)
    )
}

fun HAScriptBlocks.callMediaAction(init: CallMediaActionBlock.() -> Unit) {
    block(
        CallMediaActionBlock().apply(init)
    )
}