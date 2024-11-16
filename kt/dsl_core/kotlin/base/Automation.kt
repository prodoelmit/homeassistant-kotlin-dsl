package dsl_core.base

import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kt.dsl_core.kotlin.actions.HAAction
import kt.dsl_core.kotlin.conditions.Condition
import kt.dsl_core.kotlin.script.blocks.ConditionsConsumer
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl


@Serializable
open class Automation {
    lateinit var id: String
    var alias: String? = null
    var description: String? = null

    @SerialName("trigger")
    private val triggers = mutableListOf<Trigger>()

    @SerialName("condition")
    private val conditions = mutableListOf<Condition>()

    @SerialName("action")
    private val actions = mutableListOf<HAAction>()

    fun triggers(init: Triggers.() -> Unit) {
        val builder = Triggers()
        builder.init()
        triggers.addAll(builder.build())
    }

    fun conditions(init: ConditionsConsumer.() -> Unit) {
        ConditionsConsumer(this.conditions).apply(init)
    }

    fun actions(init: Actions.() -> Unit) {
        val builder = Actions()
        builder.init()
        actions.addAll(builder.build())
    }
}

