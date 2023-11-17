package com.example.mycalculator.presentation.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.IdRepository

class MainViewModelFactory(private val repository : IdRepository,private val actionsRepository: ActionsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository,actionsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}