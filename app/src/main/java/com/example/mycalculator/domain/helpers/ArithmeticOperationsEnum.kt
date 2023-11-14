package com.example.mycalculator.domain.helpers

enum class ArithmeticOperationsEnum(var string: String) : Buttons,ButtonsWithString{
    Divide("/"),
    Dote("."),
    Minus("-"),
    Multiplication("*"),
    Percentages("%"),
    Plus("+");

    override fun codeString() : String{
        return this.string
    }
    override fun code() : Unit{
        return
    }
}