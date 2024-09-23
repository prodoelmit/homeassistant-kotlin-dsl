package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MultiEntityTrigger(
    @SerialName("entity_id")
    val entityIds: MutableList<String>
) : Trigger("UNDEFINED_PLATFORM") {

    constructor(
        platform: String,
    ) : this(mutableListOf()) {
        this.platform = platform
    }

    fun entities(vararg entities: Entity) {
        entities.mapTo(entityIds) { it.entityId }
    }
}