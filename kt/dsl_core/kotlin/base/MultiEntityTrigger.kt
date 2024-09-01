package dsl_core.base

import kotlinx.serialization.SerialName

open class MultiEntityTrigger(platform: String): Trigger(platform) {
    @SerialName("entity_id")
    val entityIds: MutableList<String> = mutableListOf()

    fun entities(vararg entities: Entity) {
        entities.mapTo(entityIds) {it.entityId}
    }
}