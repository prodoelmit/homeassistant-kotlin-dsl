package kt.dsl_core.kotlin.ui.cards

import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.entities.CounterEntity


@Serializable
sealed class CardCondition {
    var condition: String = ""
    @SerialName("entity")
    var entityId: String = ""

    fun entity(entity: Entity) {
        if (entity is CounterEntity) {
            this.entityId = "counter.${entity.alias}"
        } else {
            this.entityId = entity.combinedEntityId
        }
    }
}

@Serializable class NumericStateCardCondition(): CardCondition() {
    init {
        condition = "numeric_state"
    }

    var above: Int? = null
    var below: Int? = null
}


fun ConditionalCardConditions.numericState(init: NumericStateCardCondition.() -> Unit) {
    condition(
        NumericStateCardCondition().apply(init)
    )
}
