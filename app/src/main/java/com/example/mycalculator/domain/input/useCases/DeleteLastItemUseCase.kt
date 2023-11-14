package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.input.MainRepository

class DeleteLastItemUseCase(private val mainRepository: MainRepository) {

        fun execute() {
            mainRepository.deleteLast()
        }
    }