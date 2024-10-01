package kt.dsl_core.kotlin.entities

import dsl_core.base.Entity
import dsl_core.base.HABoolean
import kotlinx.serialization.Serializable

@Serializable
class InputBooleanEntity(
    var initial: HABoolean = HABoolean.OFF
)
: Entity() {
    constructor(entityId: String,
        initial: HABoolean): this(initial) {
        entityId(entityId)
        }

    init {
        type("input_boolean")
    }

}