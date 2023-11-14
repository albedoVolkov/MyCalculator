package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.input.MainRepository


class EditItemUseCase(private val mainRepository: MainRepository)  {

    fun execute(item : Item) {
        mainRepository.editItem(item)
    }
}