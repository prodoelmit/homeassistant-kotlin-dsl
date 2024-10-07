package kt.dsl_core.kotlin.conditions

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class NumericStateCondition(): Condition() {
    init {
        condition = "numeric_state"
    }

    var above: Int? = null
    var below: Int? = null
}


fun ConditionsDsl.numericState(init: NumericStateCondition.() -> Unit) {
    condition(
        NumericStateCondition().apply(init)
    )
}
