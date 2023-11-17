package com.example.mycalculator.domain.usecases.IdRepository

import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.IdRepository

class CreateIdForActionItem(private val repository: IdRepository) {
    suspend fun execute() : String{
        return repository.idAction()
    }
}