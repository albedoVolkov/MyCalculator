package com.example.mycalculator.domain.helpers


data class Item(
    var isPressed: Boolean = false,
    var id: Long = UNDEFINED_ID,
    var action: ButtonsWithString
    ){
    companion object{
       const val UNDEFINED_ID = -1L
    }
    override fun toString(): String {
        return "Item [id: ${this.id}," +
                " text: ${this.action}," +
                " isPressed: ${this.isPressed}]"
    }
}