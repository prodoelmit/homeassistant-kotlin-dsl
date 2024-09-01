package dsl_core.base

import kotlinx.serialization.SerialName

open class SingleEntityTrigger(platform: String): Trigger(platform) {
    @SerialName("entity_id")
    lateinit var entityId: String

    fun entity(entity: Entity) {
        entityId = entity.entityId
    }
}