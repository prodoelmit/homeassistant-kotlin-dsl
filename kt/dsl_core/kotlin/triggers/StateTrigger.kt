package dsl_core.base

import dsl_core.base.Trigger
import kotlinx.serialization.Serializable


@Serializable
class StateTrigger : MultiEntityTrigger("state") {
    object State {
        val ON = "on"
        val OFF = "off"
    }

    var transitionFrom: String? = null
    var transitionTo: String? = null
}

fun Triggers.stateTrigger(init: StateTrigger.() -> Unit) {
    trigger(StateTrigger().apply(init))
}

