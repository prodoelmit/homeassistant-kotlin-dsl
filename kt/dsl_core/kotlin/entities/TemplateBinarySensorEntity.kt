package kt.dsl_core.kotlin.entities

import dsl_core.base.HADuration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TemplateBinarySensorEntity: BinarySensorEntity() {
    var state: String = ""
    init {
        platform = "template"
    }
}