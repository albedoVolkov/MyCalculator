package com.example.mycalculator.domain.usecases.ActionsRepository

import com.example.mycalculator.domain.ActionsRepository

class DeleteAllItemsFromListUseCase(private val repository: ActionsRepository) {
    suspend fun execute(){
        repository.deleteAllItems()
    }
}