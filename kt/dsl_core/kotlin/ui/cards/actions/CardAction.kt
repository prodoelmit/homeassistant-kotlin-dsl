package kt.dsl_core.kotlin.ui.cards.actions

import kotlinx.serialization.SerialName
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
    @SerialName("action")
    var actionType: String = "toggle"

    fun action(action: Actions) {
        this.actionType = action.value
    }
}

class CardActionDsl(val consumer: (CardAction) -> Unit) {
    fun action(existingAction: CardAction) {
        consumer(existingAction)
    }
}