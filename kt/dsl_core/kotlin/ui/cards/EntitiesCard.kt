package kt.dsl_core.kotlin.ui.cards

import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class EntitiesCard : Card() {
    init {
        this.type = "entities"
    }

    var entities: MutableList<EntityData> = mutableListOf()

    @Serializable
    class EntityData {
        @SerialName("entity")
        var entityId: String = ""
        var type: String? = null
    }

    fun entity(entity: Entity, init: EntityData.() -> Unit = {}) {
        entities.add(
            EntityData()
                .apply { entityId = entity.id() }
                .apply(init)
        )
    }
}

fun Cards.entities(init: EntitiesCard.() -> Unit) {
    card(EntitiesCard().apply(init))
}