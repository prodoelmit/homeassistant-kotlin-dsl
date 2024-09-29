package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable

@Serializable
sealed class CardAction {
    enum class Actions(val value: String) {
        MoreInfo("more-info"),
        Toggle("toggle"),
        PerformAction("perform-action"),
        Navigate("navigate"),
        Url("url"),
        Assist("assist"),
        None("none")
    }
    var action: String = "toggle"

    fun action(action: Actions) {
        this.action = action.value
    }
}