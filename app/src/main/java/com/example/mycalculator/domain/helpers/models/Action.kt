package com.example.mycalculator.domain.helpers.models

class Action(
    var idItem: String,
    var  expression: String,
    var  result: String){
    override fun toString(): String {
        return "Action : ($idItem , $expression , $result)"
    }
}