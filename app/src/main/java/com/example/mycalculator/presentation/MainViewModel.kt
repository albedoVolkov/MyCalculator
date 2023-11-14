package com.example.mycalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mycalculator.data.MainRepositoryImpl
import com.example.mycalculator.domain.helpers.Buttons
import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.helpers.OperationsEnum
import com.example.mycalculator.domain.input.useCases.AddItemUseCase
import com.example.mycalculator.domain.input.useCases.DeleteAllItemsUseCase
import com.example.mycalculator.domain.input.useCases.DeleteFirstUseCase
import com.example.mycalculator.domain.input.useCases.DeleteItemUseCase
import com.example.mycalculator.domain.input.useCases.DeleteLastItemUseCase
import com.example.mycalculator.domain.input.useCases.EditItemUseCase
import com.example.mycalculator.domain.input.useCases.GetItemByIdUseCase
import com.example.mycalculator.domain.input.useCases.GetListUseCase

class MainViewModel : ViewModel() {
    fun edit(item : Item) {
        EditItemUseCase(mainRepository = MainRepositoryImpl).execute(item)
    }

    fun insert(item : Item) {
        AddItemUseCase(mainRepository = MainRepositoryImpl).execute(item)
    }

    fun delete(item : Item) {
        DeleteItemUseCase(mainRepository = MainRepositoryImpl).execute(item)
    }

    fun changeIsPressed(item : Item, statusNew : Boolean) {
        val newTask = item.copy(isPressed = statusNew)
        EditItemUseCase(mainRepository = MainRepositoryImpl).execute(newTask)
    }

    fun deleteAll() {
        DeleteAllItemsUseCase(mainRepository = MainRepositoryImpl).execute()
    }

    fun deleteFirst() {
        DeleteFirstUseCase(mainRepository = MainRepositoryImpl).execute()
    }

    fun getList() : LiveData<List<Item>> {
        return GetListUseCase(mainRepository = MainRepositoryImpl).execute()
    }

    fun getItemById(id : Long) : Item {
        return GetItemByIdUseCase(mainRepository = MainRepositoryImpl).execute(id)
    }

    fun deleteLast(){
        DeleteLastItemUseCase(mainRepository = MainRepositoryImpl).execute()
    }

}