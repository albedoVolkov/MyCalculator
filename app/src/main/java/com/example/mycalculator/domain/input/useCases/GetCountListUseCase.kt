package com.example.mycalculator.domain.input.useCases

import com.example.mycalculator.domain.input.MainRepository

class GetCountListUseCase(private val mainRepository: MainRepository) {
        fun execute() : Int {
            return mainRepository.getCountList()
        }
}