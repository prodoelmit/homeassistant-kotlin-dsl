package kt.dsl_core.kotlin.ui.cards

import dsl_core.base.Entity
import kotlinx.serialization.Serializable


@Serializable
class EntityCard : Card() {
    init {
        this.type = "entity"
    }

    var entity: EntitiesCard.EntityData? = null


    fun entity(entity: Entity, init: EntitiesCard.EntityData.() -> Unit = {}) {
        this.entity =
            EntitiesCard.EntityData()
                .apply { entityId = entity.id() }
                .apply(init)

    }
}

fun Cards.entity(init: EntityCard.() -> Unit) {
    card(EntityCard().apply(init))
}