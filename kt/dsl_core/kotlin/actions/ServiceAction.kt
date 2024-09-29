package kt.dsl_core.kotlin.actions

import dsl_core.base.Actions
import dsl_core.base.Entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
    - service: todo.update_item
      metadata: {}
      data:
        item: Кошачий ужин
        status: completed
      target:
        entity_id: todo.ararat_6
 */
@Serializable
open class ServiceAction(
): HAAction {

    var service: String = ""
    var metadata: MutableMap<String, String> = mutableMapOf()
    var data: MutableMap<String, String> = mutableMapOf()
    var target: Target? = null

    @Serializable
    class Target(
        @SerialName("entity_id")
        var entityId: String
    )

    fun target(entity: Entity) {
        if (target == null) {
            target = ServiceAction.Target(entity.combinedEntityId)
        } else {
            target!!.entityId = entity.combinedEntityId
        }
    }
}

fun Actions.service(init: ServiceAction.() -> Unit) {
    this.action(ServiceAction().apply(init))
}