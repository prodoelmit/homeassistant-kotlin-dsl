package dsl_core.base

import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString


@Serializable
open class Automation {
    lateinit var id: String
    var alias: String? = null
    var description: String? = null

    private val triggers = mutableListOf<Trigger>()

    fun triggers(init: Triggers.() -> Unit) {
        val builder = Triggers()
        builder.init()
        triggers.addAll(builder.build())
    }
}
