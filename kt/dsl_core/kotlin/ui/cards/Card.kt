package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable

@Serializable
sealed class Card {
    var type: String = ""
}
