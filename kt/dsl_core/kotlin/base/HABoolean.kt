package dsl_core.base
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable(with = HABooleanSerializer::class)
class HABoolean {
    var value: Boolean = false

    constructor()

    constructor(value: Boolean) {
        this.value = value
    }

    override fun toString(): String {
        return if (value) "on" else "off"
    }

    companion object {
        fun fromString(boolString: String): HABoolean {
            return when (boolString.lowercase()) {
                "on" -> HABoolean(true)
                "off" -> HABoolean(false)
                else -> throw IllegalArgumentException("Invalid HABoolean string: $boolString")
            }
        }

        val ON = HABoolean(true)
        val OFF = HABoolean(false)
    }
}

object HABooleanSerializer : KSerializer<HABoolean> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("HABoolean", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: HABoolean) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): HABoolean {
        val string = decoder.decodeString()
        return HABoolean.fromString(string)
    }
}
