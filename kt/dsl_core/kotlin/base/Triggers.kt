package dsl_core.base


class Triggers {
    private val triggers = mutableListOf<Trigger>()
    fun trigger(trigger: Trigger) {
        triggers.add(trigger)
    }

    fun build(): List<Trigger> = triggers
}