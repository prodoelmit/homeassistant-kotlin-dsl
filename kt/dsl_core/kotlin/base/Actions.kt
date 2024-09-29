package dsl_core.base

import kt.dsl_core.kotlin.actions.HAAction


class Actions {
    private val actions = mutableListOf<HAAction>()
    fun action(action: HAAction) {
        actions.add(action)
    }

    fun build(): List<HAAction> = actions
}