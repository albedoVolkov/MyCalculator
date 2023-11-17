package com.example.mycalculator.domain.usecases.ActionsRepository

import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.helpers.models.Action

class DeleteActionFromListUseCase(private val repository: ActionsRepository) {
    suspend fun execute(id : String){
        repository.deleteItem(id)
    }
}