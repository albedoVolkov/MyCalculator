package com.example.mycalculator.domain.input.useCases

import androidx.lifecycle.LiveData
import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.input.MainRepository

class GetListUseCase(private val mainRepository: MainRepository) {
    fun execute() : LiveData<List<Item>> {
        return mainRepository.getList()
    }
}