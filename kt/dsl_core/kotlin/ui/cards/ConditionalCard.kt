package kt.dsl_core.kotlin.ui.cards

import kotlinx.serialization.Serializable
import kt.dsl_core.kotlin.conditions.Condition
import kt.dsl_core.kotlin.ui.View

@Serializable
class ConditionalCard(): Card() {
    var conditions = mutableListOf<Condition>()
    var card: Card? = null
    init {
        type = "conditional"
    }

    fun conditions(init: ConditionalCardConditions.() -> Unit) {
        ConditionalCardConditions(this).apply(init)
    }

    /*
    Reuse `cards { }` DSL block, but only take first card
     */
    fun cards(init: Cards.() -> Unit) {
        val fakeView = View()
        Cards(fakeView).apply(init)
        assert(fakeView.cards.count() == 1)
        this.card = fakeView.cards.first()
    }
}

fun Cards.conditional(init: ConditionalCard.() -> Unit) {
    card(
        ConditionalCard().apply(init)
    )
}

class ConditionalCardConditions(val card: ConditionalCard): ConditionsDsl {
    override fun condition(existingCondition: Condition) {
        card.conditions.add(existingCondition)
    }
}

interface ConditionsDsl {
    fun condition(existingCondition: Condition)
}

