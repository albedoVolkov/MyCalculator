package com.example.mycalculator.domain.helpers

enum class ArithmeticOperationsEnum(var string: Char) : Buttons,ButtonsWithString{
    Divide('/'),
    Dote('.'),
    Minus('-'),
    Multiplication('*'),
    Percentages('%'),
    Plus('+');

    override fun codeString() : Char{
        return this.string
    }
    override fun code(){
        return
    }
}