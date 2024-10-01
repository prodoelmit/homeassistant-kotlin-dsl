package dsl_core.base

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kt.dsl_core.kotlin.script.blocks.HAScriptBlock

@Serializable
class HAScript {
    @Transient
    private
    var myId: String = ""
    var alias: String = ""
    val sequence: MutableList<HAScriptBlock> = mutableListOf()


    fun id(value: String) {
        myId = value
    }

    fun id(): String {
        return myId
    }


    val absoluteId: String
        get() = "script.$myId"



    constructor(init: HAScript.() -> Unit) {
        apply(init)
    }

    fun blocks(init: HAScriptBlocks.() -> Unit) {
        HAScriptBlocks(this).apply(init)
    }
}

fun HAProject.script(init: HAScript.() -> Unit) {
    script(
        HAScript(init)
    )
}




class HAScriptBlocks(val script: HAScript) {
    fun block(existingBlock: HAScriptBlock) {
        script.sequence.add(existingBlock)
    }
}