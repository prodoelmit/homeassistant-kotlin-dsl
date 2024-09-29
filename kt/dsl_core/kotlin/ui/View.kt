package kt.dsl_core.kotlin.ui

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.cards.Card

@Serializable
class View {
    var title: String = ""
    var path: String = ""
    val cards = mutableListOf<Card>()
}