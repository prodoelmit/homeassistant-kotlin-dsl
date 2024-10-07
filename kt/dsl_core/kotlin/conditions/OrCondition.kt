package kt.dsl_core.kotlin.conditions

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.script.blocks.ConditionsConsumer
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class OrCondition: Condition() {
    var conditions: MutableList<Condition> = mutableListOf()
    init {
        condition = "or"
    }
}

fun ConditionsDsl.or(init: ConditionsConsumer.() -> Unit) {
    val orCondition = OrCondition()
    val consumer = ConditionsConsumer(orCondition.conditions)
    consumer.apply(init)
    condition(orCondition)
}
