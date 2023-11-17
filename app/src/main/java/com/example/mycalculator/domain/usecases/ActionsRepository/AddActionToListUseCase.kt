package com.example.mycalculator.domain.usecases.ActionsRepository

import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.helpers.models.Action

class AddActionToListUseCase(private val repository: ActionsRepository) {
    suspend fun execute(item : Action){
        repository.addItem(item)
    }
}