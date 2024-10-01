package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
open class Entity(
) {
    @SerialName("id")
    private var combinedEntityId: String = ""

    fun refreshCombinedEntityId() {
        combinedEntityId = "$myType.$myEntityId"
    }

    open fun id(): String {
        return combinedEntityId
    }

    var name: String = ""
    @Transient
    private var myEntityId: String = ""

    final fun entityId(value: String) {
        this.myEntityId = value
        refreshCombinedEntityId()
    }

//    final fun entityId(): String {
//        return myEntityId
//    }

    var icon: String = ""
    @Transient
    private var myType: String = ""

    fun type(): String {
        return myType
    }

    fun type(value: String) {
        myType = value
        refreshCombinedEntityId()
    }


    companion object {
        fun forTypeAndEntityId(type: String, entityId: String): Entity {
            return Entity().apply {
                type(type)
                entityId(entityId)
            }
        }

        fun forId(combinedEntityId: String): Entity {
            return Entity().apply {
                this.combinedEntityId = combinedEntityId
            }
        }
    }
}
