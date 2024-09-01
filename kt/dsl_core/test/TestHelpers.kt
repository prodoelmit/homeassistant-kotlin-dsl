package dsl_core

import dsl_core.base.toYaml

inline fun <reified T> assertRenders(what: T, expected: String) {
    val actual = toYaml(what)
    assert(actual == expected) {
        "Got:\n---\n$actual\nExpected:\n---\n$expected"
    }

}