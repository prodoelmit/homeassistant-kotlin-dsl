package kt.dsl_core.kotlin.ui.cards

import dsl_core.base.Entities
import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.ui.View


@Serializable
class EntityCard : Card() {
    init {
        this.type = "entity"
    }

    var entity: EntitiesCard.EntityData? = null


    fun entity(entity: Entity, init: EntitiesCard.EntityData.() -> Unit = {}) {
        this.entity =
            EntitiesCard.EntityData()
                .apply { entityId = entity.combinedEntityId }
                .apply(init)

    }
}

fun Cards.entity(init: EntityCard.() -> Unit) {
    card(EntityCard().apply(init))
}