package dsl_core.base
import com.charleskorn.kaml.PolymorphismStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.encodeToString

inline fun <reified T> toYaml(value: T): String {
    val configuration = YamlConfiguration(
        polymorphismStyle = PolymorphismStyle.None,
        encodeDefaults = false
    )
    return Yaml(configuration = configuration).encodeToString(value)
}
