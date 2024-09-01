package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class Trigger(
    val platform: String,
) {
    @SerialName("entity_id")
    val entityIds: MutableList<String> = mutableListOf()

    fun entities(vararg entities: Entity) {
        entities.mapTo(entityIds) {it.entityId}
    }
}
