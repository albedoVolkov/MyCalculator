package com.example.mycalculator.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.helpers.Item.Companion.UNDEFINED_ID
import com.example.mycalculator.domain.input.MainRepository

object MainRepositoryImpl : MainRepository {

    private var itemsLD = MutableLiveData<List<Item>>()
    private var items = mutableListOf<Item>()
    private var counterId = 0L

    override fun getList(): LiveData<List<Item>> {
        return itemsLD
    }

    override fun addItem(item: Item) {
        Log.d("Log_App", "\t addTask() add \n\t $item")
        if (item.id == UNDEFINED_ID){
            item.id = counterId
            counterId++
        }
        items.add(0,item)
        updateList()
    }

    override fun deleteItem(item: Item) {
        Log.d("Log_App", "\t deleteItem() delete \n\t $item")
        items.remove(item)
        updateList()
    }

    override fun deleteLast() {
        if (items.size != 0){
            items.removeFirst()
        }
        updateList()
    }

    override fun deleteFirst() {
        if (items.size != 0){
            items.removeLast()
        }
        updateList()
    }

    override fun deleteAllItems() {
        items.clear()
        updateList()
    }

    override fun editItem(item: Item) {
        val taskOld = getItemById(item.id)
        deleteItem(taskOld)
        addItem(item)
    }

    override fun getItemById(idOld: Long) : Item {
        for( item in items){
            if (item.id == idOld){
                return item
            }
        }
        throw RuntimeException("Item with id $idOld not found")
    }


    private fun updateList() {
        val lengthComparator = Comparator { task: Item, task2: Item -> (task2.id - task.id).toInt() }
        itemsLD.value = items.sortedWith(lengthComparator).toList()
        Log.d("Log_App", "\t updateList() save this "+ getList().value.toString())
    }

    override fun getCountList() : Int{
        return items.size
    }

    override fun setCountId(countNew : Long){
        counterId = countNew
    }

    override fun setList(listNew: List<Item>) {
        items = listNew.toMutableList()
        updateList()
    }

}