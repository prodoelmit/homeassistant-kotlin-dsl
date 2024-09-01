package dsl_core.base

import com.charleskorn.kaml.YamlMap
import com.charleskorn.kaml.YamlNode
import kotlinx.serialization.Serializable


@Serializable
class StateTrigger : Trigger("state") {
    object State {
        val ON = "on"
        val OFF = "off"
    }

    var transitionFrom: String? = null
    var transitionTo: String? = null

}