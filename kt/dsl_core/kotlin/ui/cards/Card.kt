package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Card {
    var type: String = ""
    @SerialName("view_layout")
    var viewLayout: MutableMap<String, String> = mutableMapOf()

    fun setPosition(position: Position) {
        viewLayout["position"] = position.value
    }

    enum class Position(val value: String) {
        Main("main"),
        Sidebar("sidebar")
    }
}
