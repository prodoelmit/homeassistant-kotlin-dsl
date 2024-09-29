package kt.dsl_core.kotlin.ui.cards

import kt.dsl_core.kotlin.ui.View

class Cards(val view: View) {
    // This isn't a real struct, just a helper to work on view.cards
    fun card(existingCard: Card) {
        view.cards.add(existingCard)
    }
}

fun View.cards(init: Cards.() -> Unit) {
    Cards(view = this).apply(init)
}