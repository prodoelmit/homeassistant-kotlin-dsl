package kt.dsl_core.kotlin.entities

import dsl_core.base.HADuration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TimeOfDaySensor: BinarySensorEntity() {
    var after: String = "sunrise"
    @SerialName("after_offset")
    var afterOffset: HADuration = HADuration()
    var before: String = "sunset"
    @SerialName("before_offset")
    var beforeOffset: HADuration = HADuration()
    init {
        platform = "tod"
    }
}