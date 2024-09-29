package dsl_core.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NfcTagTrigger(): Trigger("tag") {
    @SerialName("tag_id")
    var tagId: String = ""

    fun tag(tag: NfcTag) {
        tagId = tag.tagId
    }
}

fun Triggers.nfcTag(init: NfcTagTrigger.() -> Unit) {
    trigger(NfcTagTrigger().apply(init))
}