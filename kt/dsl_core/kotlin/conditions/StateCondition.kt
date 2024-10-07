package kt.dsl_core.kotlin.conditions

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class StateCondition(): Condition() {
    init {
        condition = "state"
    }

    var state: String = ""

}

fun ConditionsDsl.state(init: StateCondition.() -> Unit) {
    condition(
        StateCondition().apply(init)
    )
}
