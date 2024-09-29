package kt.dsl_core.kotlin.actions

import dsl_core.base.Actions
import dsl_core.base.Entity


object ToDoActions {
    enum class ToDoStatus(val value: String) {
        Completed("completed")
    }

    open class Base(): ServiceAction() {
        fun target(entity: Entity) {
            this.entityId = entity.combinedEntityId
        }
    }

    class UpdateItem(val init: UpdateItem.() -> Unit): Base() {
        init {
            service = "todo.update_item"

            init(this)
        }

        fun status(status: ToDoStatus) {
            data["status"] = status.value
        }

        fun todoItem(name: String) {
            data["item"] = name
        }
    }
}

fun Actions.todoUpdateItem(init: ToDoActions.UpdateItem.() -> Unit) {
    action(
        ToDoActions.UpdateItem(init)
    )
}