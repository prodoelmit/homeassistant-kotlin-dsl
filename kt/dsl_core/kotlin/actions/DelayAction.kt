package kt.dsl_core.kotlin.actions

import dsl_core.base.Actions
import dsl_core.base.HADuration
import kotlinx.serialization.Serializable

/*
  - delay:
      hours: 0
      minutes: 0
      seconds: 1
      milliseconds: 0
 */
@Serializable
class DelayAction: HAAction {
    var delay: HADuration = HADuration()
}

fun Actions.delay(init: HADuration.() -> Unit) {
    action(
        DelayAction().apply {
            delay = HADuration().apply(init)
        }
    )
}