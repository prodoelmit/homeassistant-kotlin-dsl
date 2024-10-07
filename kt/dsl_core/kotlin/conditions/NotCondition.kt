package kt.dsl_core.kotlin.conditions

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.script.blocks.ConditionsConsumer
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class NotCondition: Condition() {
    var conditions: MutableList<Condition> = mutableListOf()
    init {
        condition = "not"
    }
}

fun ConditionsDsl.not(init: ConditionsConsumer.() -> Unit) {
    val notCondition = NotCondition()
    val consumer = ConditionsConsumer(notCondition.conditions)
    consumer.apply(init)
    condition(notCondition)
}
