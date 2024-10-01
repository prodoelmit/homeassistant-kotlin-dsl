package kt.dsl_core.kotlin.entities

import dsl_core.base.Entity
import dsl_core.base.HABoolean
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class InputBooleanEntity(
    var initial: HABoolean = HABoolean.OFF
)
: Entity() {
    constructor(alias: String,
                initial: HABoolean): this(initial) {
            this.alias = alias
        }

    @Transient
    var alias: String = ""

    override fun id(): String {
        return "input_boolean.$alias"
    }


    init {
        this.clearCombinedEntityId()
    }
}