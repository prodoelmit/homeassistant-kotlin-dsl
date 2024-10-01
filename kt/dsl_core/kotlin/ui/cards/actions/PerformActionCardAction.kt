package kt.dsl_core.kotlin.ui.cards.actions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PerformActionCardAction: CardAction() {
    init {
        this.actionType = "perform-action"
    }
    @SerialName("perform_action")
    var actionToPerform: String = ""

    // Just an empty map? Can we omit it?
    var target: Map<String, String> = mapOf()
}

fun CardActionDsl.performAction(init: PerformActionCardAction.() -> Unit) {
    action(
        PerformActionCardAction().apply(init)
    )
}