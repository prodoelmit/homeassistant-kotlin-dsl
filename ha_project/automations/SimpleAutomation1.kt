package ha_project.automations

import dsl_core.base.Automation
import dsl_core.base.numericStateTrigger

/*
- id: '1725223294684'
  alias: dsl-test-1
  description: ''
  trigger:
  - platform: sun
    event: sunrise
    offset: 00:10:00
  condition:
  - condition: numeric_state
    entity_id: todo.ararat_6
    above: 3
  action:
  - service: notify.mobile_app_sm_s918b
    metadata: {}
    data:
      message: 'there are at least 3 tasks to do '
  mode: single
 */
class SimpleAutomation1: Automation() {
    init {
        triggers {
            numericStateTrigger {
                entity
            }

        }

    }
}