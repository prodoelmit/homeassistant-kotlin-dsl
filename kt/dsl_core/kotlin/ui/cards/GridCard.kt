package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.View

@Serializable
class GridCard: Card() {
    var cards: MutableList<Card> = mutableListOf()
    init {
        type = "grid"
    }

    fun cards(init: Cards.() -> Unit) {
        val fakeView = View()
        Cards(fakeView).apply(init)
        this.cards.addAll(fakeView.cards)
    }
}

fun Cards.grid(init: GridCard.() -> Unit) {
    card(
        GridCard().apply(init)
    )
}