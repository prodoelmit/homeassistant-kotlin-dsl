package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.actions.HAAction
import kt.dsl_core.kotlin.ui.cards.actions.CardAction
import kt.dsl_core.kotlin.ui.cards.actions.CardActionDsl

@Serializable
class MushroomTemplateCard: Card() {
    init {
        this.type = "custom:mushroom-template-card"
    }

    var primary: String? = null
    var secondary: String? = null
    var icon: String? = null
    @SerialName("icon_color")
    var iconColor: String? = null

    @SerialName("tap_action")
    var tapAction: CardAction? = null
    fun tapAction(init: CardActionDsl.() -> Unit) {
        CardActionDsl { action ->
            tapAction = action
        }.apply(init)
    }

    @SerialName("hold_action")
    var holdAction: CardAction? = null
    fun holdAction(init: CardActionDsl.() -> Unit) {
        CardActionDsl { action ->
            holdAction = action
        }.apply(init)
    }

    @SerialName("double_tap_action")
    var doubleTapAction: CardAction? = null
    fun doubleTapAction(init: CardActionDsl.() -> Unit) {
        CardActionDsl { action ->
            doubleTapAction = action
        }.apply(init)
    }
}

fun Cards.mushroomTemplateCard(init: MushroomTemplateCard.() -> Unit) {
    card(MushroomTemplateCard().apply(init))
}
