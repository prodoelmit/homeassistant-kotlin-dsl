package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.properties.Delegates

@Serializable
open class Entity(
    @SerialName("id")
    var combinedEntityId: String = "",
) {

    fun refreshCombinedEntityId() {
        combinedEntityId = "$type.$entityId"
    }

    var name: String = ""
    var entityId: String = ""
        get() = field
        set(value) {
            field = value
            refreshCombinedEntityId()
        }

    var icon: String = ""
    open var type: String = ""
        get() = field
        set(value) {
            field = value
            refreshCombinedEntityId()
        }


    companion object {
        fun forTypeAndEntityId(entityId: String, type: String): Entity {
            return Entity(combinedEntityId = "$type.$entityId")
        }
    }
}
