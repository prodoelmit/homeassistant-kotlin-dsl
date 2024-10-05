package kt.dsl_core.kotlin.ui

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.cards.Card

@Serializable
class View {
    var title: String = ""
    var path: String = ""
    val cards = mutableListOf<Card>()
    var type: Type = Type.Masonry
    @Serializable
    enum class Type() {
        @SerialName("masonry") Masonry,
        @SerialName("sidebar") Sidebar,
    }
}