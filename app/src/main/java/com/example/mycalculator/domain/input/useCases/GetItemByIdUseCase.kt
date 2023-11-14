package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.input.MainRepository


class GetItemByIdUseCase(private val mainRepository: MainRepository) {
    fun execute(Id : Long): Item {
        return mainRepository.getItemById(Id)
    }
}