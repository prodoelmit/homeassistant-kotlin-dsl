package dsl_core.base

import kotlinx.serialization.Serializable

@Serializable
class TimePatternTrigger: Trigger("time_pattern") {
    var hours: String = "0"
    var minutes: String = "0"
    var seconds: String = "0"

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