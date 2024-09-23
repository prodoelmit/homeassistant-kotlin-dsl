package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed class Trigger(
    open var platform: String,
) {
}
