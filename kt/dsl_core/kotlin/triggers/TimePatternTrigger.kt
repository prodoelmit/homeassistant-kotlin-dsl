package dsl_core.base

import kotlinx.serialization.Serializable

@Serializable
class TimePatternTrigger: Trigger("time_pattern") {
    var hours: String? = null
    var minutes: String? = null
    var seconds: String? = null

    fun hours(hours: Int) {
        this.hours = hours.toString()
    }

    fun minutes(minutes: Int) {
        this.minutes = minutes.toString()
    }

    fun seconds(seconds: Int) {
        this.seconds = seconds.toString()
    }
}

fun Triggers.timePattern(init: TimePatternTrigger.() -> Unit) {
    trigger(TimePatternTrigger().apply(init))
}