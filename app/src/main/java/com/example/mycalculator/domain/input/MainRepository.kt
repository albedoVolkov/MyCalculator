package com.example.mycalculator.domain.input

import androidx.lifecycle.LiveData
import com.example.mycalculator.domain.helpers.Item

interface MainRepository {

    fun getList(): LiveData<List<Item>>

    fun addItem(item: Item)

    fun deleteItem(item: Item)

    fun deleteLast()

    fun deleteFirst()

    fun deleteAllItems()

    fun editItem(item: Item)

    fun getItemById(idOld: Long) : Item

    fun getCountList() : Int

    fun setCountId(countNew : Long)

    fun setList(listNew: List<Item>)

}