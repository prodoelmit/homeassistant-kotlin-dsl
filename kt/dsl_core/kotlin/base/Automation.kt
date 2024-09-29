package dsl_core.base

import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kt.dsl_core.kotlin.actions.HAAction


@Serializable
open class Automation {
    lateinit var id: String
    var alias: String? = null
    var description: String? = null

    @SerialName("trigger")
    private val triggers = mutableListOf<Trigger>()

    @SerialName("action")
    private val actions = mutableListOf<HAAction>()

    fun triggers(init: Triggers.() -> Unit) {
        val builder = Triggers()
        builder.init()
        triggers.addAll(builder.build())
    }

    fun actions(init: Actions.() -> Unit) {
        val builder = Actions()
        builder.init()
        actions.addAll(builder.build())
    }
}
