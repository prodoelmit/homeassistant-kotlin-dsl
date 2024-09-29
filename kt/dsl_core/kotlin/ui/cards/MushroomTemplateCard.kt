package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.actions.HAAction

@Serializable
class MushroomTemplateCard: Card() {
    init {
        this.type = "custom:mushroom-template-card"
    }

    var primary: String? = null
    var secondary: String? = null
    var icon: String? = null
    var icon_color: String? = null
    var tap_action: CardAction? = null
    var hold_action: CardAction? = null
}

fun Cards.mushroomTemplateCard(init: MushroomTemplateCard.() -> Unit) {
    card(MushroomTemplateCard().apply(init))
}
