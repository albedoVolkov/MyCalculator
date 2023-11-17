package com.example.mycalculator.domain.usecases.ActionsRepository

import androidx.lifecycle.LiveData
import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.helpers.models.Action

class GetListOfActionsUseCase(private val repository: ActionsRepository) {
    suspend fun execute(): LiveData<List<Action>>{
        return repository.getList()
    }
}