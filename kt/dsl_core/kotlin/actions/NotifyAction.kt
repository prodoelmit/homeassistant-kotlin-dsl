package kt.dsl_core.kotlin.actions

import dsl_core.base.Actions

class NotifyAction(val init: NotifyAction.() -> Unit): ServiceAction() {
    init {
        init(this)
    }

    fun message(message: String) {
        data["message"] = message
    }
}

fun Actions.notify(init: NotifyAction.() -> Unit) {
    action(NotifyAction(init))
}