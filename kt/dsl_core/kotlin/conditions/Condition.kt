package kt.dsl_core.kotlin.conditions

import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl


@Serializable
sealed class Condition {
    var condition: String = ""
    // This field should be named `entity_id` when used in scripts and in automation.conditions
    // For now I just `sed` it after rendering
    @SerialName("entity")
    var entityId: String = ""

    fun entity(entity: Entity) {
        this.entityId = entity.id()
    }
}
