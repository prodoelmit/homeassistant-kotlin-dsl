package dsl_core.base
class TriggersBuilder {
    private val triggers = mutableListOf<Trigger>()

    fun stateTrigger(init: StateTrigger.() -> Unit) {
        triggers.add(StateTrigger().apply(init))
    }

    fun build(): List<Trigger> = triggers
}