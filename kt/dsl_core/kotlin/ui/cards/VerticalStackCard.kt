package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.View

@Serializable
class VerticalStackCard: Card() {
    var cards: MutableList<Card> = mutableListOf()
    init {
        type = "vertical-stack"
    }

    fun cards(init: Cards.() -> Unit) {
        val fakeView = View()
        Cards(fakeView).apply(init)
        this.cards.addAll(fakeView.cards)
    }
}

fun Cards.verticalStack(init: VerticalStackCard.() -> Unit) {
    card(
        VerticalStackCard().apply(init)
    )
}