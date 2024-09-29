package dsl_core.base

import dsl_core.base.Trigger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class StateTrigger : MultiEntityTrigger("state") {
    object State {
        val ON = "on"
        val OFF = "off"
    }

    @SerialName("from")
    var transitionFrom: String? = null
    @SerialName("to")
    var transitionTo: String? = null
}

fun Triggers.stateTrigger(init: StateTrigger.() -> Unit) {
    trigger(StateTrigger().apply(init))
}

