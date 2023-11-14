package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.input.MainRepository

class SetCountIdUseCase(private val mainRepository: MainRepository) {
    fun execute(countNew : Long){
        mainRepository.setCountId(countNew)
    }
}