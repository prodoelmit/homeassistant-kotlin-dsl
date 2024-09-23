package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Entity(
    @SerialName("name")
    var combinedEntityId: String = ""
) {
    fun refreshCombinedEntityId() {
        combinedEntityId = "$type.$entityId"
    }
    var entityId: String = ""
        get() = field
        set(value) {
            field = value
            refreshCombinedEntityId()
        }
    var type: String = ""
        get() = field
        set(value) {
            field = value
            refreshCombinedEntityId()
        }
    constructor(entityId: String, type: String): this(combinedEntityId = "$type.$entityId") {

    }
}
