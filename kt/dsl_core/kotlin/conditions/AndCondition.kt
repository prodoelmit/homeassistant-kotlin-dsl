package kt.dsl_core.kotlin.conditions

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.script.blocks.ConditionsConsumer
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class AndCondition: Condition() {
    var conditions: MutableList<Condition> = mutableListOf()
    init {
        condition = "and"
    }
}

fun ConditionsDsl.and(init: ConditionsConsumer.() -> Unit) {
    val andCondition = AndCondition()
    val consumer = ConditionsConsumer(andCondition.conditions)
    consumer.apply(init)
    condition(andCondition)
}
