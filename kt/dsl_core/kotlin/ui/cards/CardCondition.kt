package kt.dsl_core.kotlin.ui.cards

import dsl_core.base.Entity
import kotlinx.serialization.SealedClassSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class CardCondition {
    var condition: String = ""
    @SerialName("entity")
    var entityId: String = ""

    fun entity(entity: Entity) {
        this.entityId = entity.id()
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

@Serializable class StateCardCondition(): CardCondition() {
    init {
        condition = "state"
    }

    var state: String = ""

}

fun ConditionalCardConditions.state(init: StateCardCondition.() -> Unit) {
    condition(
        StateCardCondition().apply(init)
    )
}
