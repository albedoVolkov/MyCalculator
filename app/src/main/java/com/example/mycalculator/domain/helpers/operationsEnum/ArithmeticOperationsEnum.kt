package com.example.mycalculator.domain.helpers.operationsEnum

import com.example.mycalculator.domain.helpers.operationsEnum.basedClasses.Buttons
import com.example.mycalculator.domain.helpers.operationsEnum.basedClasses.ButtonsWithString

enum class ArithmeticOperationsEnum(var string: Char) : Buttons, ButtonsWithString {
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