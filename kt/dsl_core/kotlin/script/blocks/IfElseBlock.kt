package kt.dsl_core.kotlin.script.blocks

import dsl_core.base.Actions
import dsl_core.base.ScriptBlocksConsumer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.actions.HAAction
import kt.dsl_core.kotlin.conditions.Condition
import kt.dsl_core.kotlin.ui.cards.ConditionsDsl

@Serializable
class IfElseBlock : HAScriptBlock {
    @SerialName("if")
    var conditions: MutableList<Condition> = mutableListOf()
    @SerialName("then")
    var thenBlocks: MutableList<HAScriptBlock> = mutableListOf()
    @SerialName("else")
    var elseBlocks: MutableList<HAScriptBlock> = mutableListOf()


    fun conditions(init: ConditionsConsumer.() -> Unit) {
        ConditionsConsumer(this.conditions).apply(init)
    }

    fun thenBlocks(init: ScriptBlocksConsumer.() -> Unit) {
        ScriptBlocksConsumer(thenBlocks).apply(init)
    }

    fun elseBlocks(init: ScriptBlocksConsumer.() -> Unit) {
        ScriptBlocksConsumer(elseBlocks).apply(init)
    }
}

fun ScriptBlocksConsumer.ifElse(init: IfElseBlock.() -> Unit) {
    block(IfElseBlock().apply(init))
}

class ConditionsConsumer(val target: MutableList<Condition>) : ConditionsDsl {
    override fun condition(existingCondition: Condition) {
        target.add(existingCondition)
    }
}
