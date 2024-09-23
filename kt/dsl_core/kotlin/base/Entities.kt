package dsl_core.base

import kt.dsl_core.kotlin.entities.InputBooleanEntity

object Entities {
    fun inputBooleanEntity(init: InputBooleanEntity.() -> Unit): InputBooleanEntity = InputBooleanEntity().apply(init)
}