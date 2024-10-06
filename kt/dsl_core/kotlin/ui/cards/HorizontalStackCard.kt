package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.View

@Serializable
class HorizontalStackCard: Card() {
    var cards: MutableList<Card> = mutableListOf()
    init {
        type = "horizontal-stack"
    }

    fun cards(init: Cards.() -> Unit) {
        val fakeView = View()
        Cards(fakeView).apply(init)
        this.cards.addAll(fakeView.cards)
    }
}

fun Cards.horizontalStack(init: HorizontalStackCard.() -> Unit) {
    card(
        HorizontalStackCard().apply(init)
    )
}