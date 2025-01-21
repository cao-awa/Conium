package com.github.cao.awa.conium.datapack.inject.item.action

enum class ItemPropertyInjectAction {
    SET,
    SET_PRESET,
    MINUS,
    ADD,
    DIVIDE,
    MULTIPLY;

    companion object {
        @JvmStatic
        fun of(action: String): ItemPropertyInjectAction {
            return when (action) {
                "set" -> SET
                "set_preset" -> SET_PRESET
                "minus" -> MINUS
                "add" -> ADD
                "divide" -> DIVIDE
                "multiply" -> MULTIPLY
                else -> throw IllegalArgumentException("No that action can be completed: '$action'")
            }
        }
    }
}
