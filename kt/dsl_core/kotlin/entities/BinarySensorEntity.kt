package kt.dsl_core.kotlin.entities

import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BinarySensorEntity(
): Entity()  {
    var platform: String = ""
    @SerialName("unique_id")
    var uniqueId: String = ""

    override fun id(): String {
        return "binary_sensor.$uniqueId"
    }
}