package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.helpers.Item
import com.example.mycalculator.domain.input.MainRepository

class SetListUseCase(private val mainRepository: MainRepository) {
        fun execute(listNew : List<Item>){
            mainRepository.setList(listNew.toList())
        }
    }