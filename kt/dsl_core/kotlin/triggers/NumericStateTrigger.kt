package dsl_core.base

import kotlinx.serialization.Serializable

@Serializable
class NumericStateTrigger : SingleEntityTrigger("numeric_state") {
    var attribute: String? = null
    var above: Double? = null
    var below: Double? = null
    var valueTemplate: String? = null
    var forDuration: HADuration? = null
}

fun Triggers.numericStateTrigger(init: NumericStateTrigger.() -> Unit) {
    trigger(NumericStateTrigger().apply(init))
}
