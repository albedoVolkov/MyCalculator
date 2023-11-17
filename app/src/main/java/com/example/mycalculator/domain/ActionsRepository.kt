package com.example.mycalculator.domain

import androidx.lifecycle.LiveData
import com.example.mycalculator.domain.helpers.models.Action

interface ActionsRepository {

        suspend fun getList(): LiveData<List<Action>>

        suspend fun addItem(item: Action)

        suspend fun deleteItem(id: String)

        suspend fun deleteAllItems()

        suspend fun getItemById(idOld: String) : Action?

        suspend fun setList(listNew: List<Action>)

    }