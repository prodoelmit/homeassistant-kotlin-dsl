package dsl_core.base

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable(with = HADurationSerializer::class)
class HADuration {
    var hours = 0
    var minutes = 0
    var seconds = 0

    override fun toString(): String {
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    companion object {
        fun fromString(durationString: String): HADuration {
            val parts = durationString.split(":")
            require(parts.size == 3) { "Invalid duration format" }
            return HADuration().apply {
                hours = parts[0].toInt()
                minutes = parts[1].toInt()
                seconds = parts[2].toInt()
            }
        }
    }
}

object HADurationSerializer : KSerializer<HADuration> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("HADuration", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: HADuration) {
        val durationString = value.toString()
        encoder.encodeString(durationString)
    }

    override fun deserialize(decoder: Decoder): HADuration {
        val durationString = decoder.decodeString()
        return HADuration.fromString(durationString)
    }
}

fun duration(init: HADuration.() -> Unit): HADuration {
    return HADuration().apply(init)
}