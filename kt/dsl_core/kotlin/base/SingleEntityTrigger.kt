package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class SingleEntityTrigger(
    @SerialName("entity_id")
    var entityId: String = ""
) : Trigger("UNDEFINED_PLATFORM") {


    constructor(
        platform: String,
        entityId: String
    ) : this(entityId = entityId) {
        this.platform = platform
    }

    fun entity(entity: Entity) {
        entityId = entity.entityId
    }
}