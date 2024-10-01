package kt.dsl_core.kotlin.actions

import dsl_core.base.Actions
import dsl_core.base.HAScript
import kotlinx.serialization.Serializable

@Serializable
class ActionAction: HAAction {
    var action: String = ""
    var metadata: MutableMap<String, String> = mutableMapOf()
    var data: MutableMap<String, String> = mutableMapOf()
}

fun Actions.action(init: ActionAction.() -> Unit) {
    action(ActionAction().apply(init))
}

fun Actions.callScript(script: HAScript) {
    action {
        action = script.absoluteId
    }
}