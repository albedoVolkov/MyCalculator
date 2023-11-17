package com.example.mycalculator.domain.usecases.ActionsRepository

import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.helpers.models.Action

class SetListOfActionsUseCase(private val repository: ActionsRepository) {
    suspend fun execute(list : List<Action>){
        repository.setList(list)
    }
}