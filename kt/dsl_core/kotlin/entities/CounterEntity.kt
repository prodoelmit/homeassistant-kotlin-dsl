package kt.dsl_core.kotlin.entities

import dsl_core.base.Entity
import dsl_core.base.HABoolean
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class CounterEntity(
)
: Entity() {
    constructor(alias: String): this() {
        this.alias = alias
        }

    init {
    }

    override fun id(): String {
        return "counter.$alias"
    }


    @Transient
    var alias: String = ""

    var initial: Int = 0
    var restore: Boolean = true
    var step: Int = 1
    var minimum: Int? = null
    var maximum: Int? = null
}