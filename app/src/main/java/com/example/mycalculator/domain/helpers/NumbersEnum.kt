package com.example.mycalculator.domain.helpers

enum class NumbersEnum(var string: Char) : Buttons, ButtonsWithString{

    Nine('9'),
    Eight('8'),
    Seven('7'),
    Six('6'),
    Five('5'),
    Four('4'),
    Three('3'),
    Two('2'),
    One('1'),
    Zero('0');

    override fun codeString() : Char{
        return this.string
    }
    override fun code(){
        return
    }
}