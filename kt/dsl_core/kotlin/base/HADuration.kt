package dsl_core.base

class HADuration {
    var hours = 0
    var minutes = 0
    var seconds = 0
}

fun duration(init: HADuration.() -> Unit): HADuration {
    return HADuration().apply(init)
}