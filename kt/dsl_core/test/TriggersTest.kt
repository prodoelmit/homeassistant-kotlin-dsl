package dsl_core

import dsl_core.base.*
import org.junit.jupiter.api.Test

class TriggersTest {

    @Test
    fun `simple trigger`() {
        val entity = Entity.forId("entity1")
        val automation = Automation().apply {
            id = "entity1 state trigger"
            alias = "e1_s_t"
            triggers {
                stateTrigger {
                    this.entities(
                        entity
                    )
                    this.transitionTo = StateTrigger.State.OFF
                    this.transitionTo = StateTrigger.State.ON
                }
            }
        }

        val expected = """
            id: "entity1 state trigger"
            alias: "e1_s_t"
            description: null
            triggers:
            - platform: "state"
              entity_id:
              - "entity1"
              transitionFrom: null
              transitionTo: "on"
            """.trimIndent()

        assertRenders(automation, expected)
    }

}