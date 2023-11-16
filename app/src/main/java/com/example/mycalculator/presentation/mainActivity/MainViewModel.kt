package com.example.mycalculator.presentation.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.IdRepository
import com.example.mycalculator.domain.helpers.models.Action
import com.example.mycalculator.domain.usecases.ActionsRepository.AddActionToListUseCase
import com.example.mycalculator.domain.usecases.ActionsRepository.DeleteAllItemsFromListUseCase
import com.example.mycalculator.domain.usecases.ActionsRepository.GetActionFromListByIdUseCase
import com.example.mycalculator.domain.usecases.ActionsRepository.GetListOfActionsUseCase
import com.example.mycalculator.domain.usecases.IdRepository.CreateIdForActionItem
import kotlinx.coroutines.launch

class MainViewModel(private val idRepository: IdRepository,private val  actionsRepository: ActionsRepository) : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private var _mainText : MutableLiveData<String> = MutableLiveData<String>()
    val mainText: LiveData<String> get() = _mainText

    private var _showMainText : MutableLiveData<String> = MutableLiveData<String>()
    val showMainText: LiveData<String> get() = _showMainText

    private var _actions : MutableLiveData<List<Action>> = MutableLiveData<List<Action>>()
    val actions: LiveData<List<Action>> get() = _actions

    init {
        _mainText.value = ""
        _showMainText.value = "0.0"

        getActions()
    }


    fun filterItems(filterType: String) {
        Log.d(TAG, "filterType is $filterType and list is ${_mainText.value}")
        _showMainText.value = when (filterType) {
            "None" -> _mainText.value ?: "0.0"
            "Zero" -> "0.0"
            "Reversed" -> _mainText.value?.reversed() ?: "0.0"
            else -> { "0.0" }
        }
    }


    fun setMainText(item : String) {
        _mainText.value = item
    }

    fun insert(item : Char) {
        _mainText.value += item
    }

    fun deleteAll() {
        _mainText.value = ""
    }

    fun deleteLast(){
        val element = _mainText.value ?: "0"
        if(element != "0") {
            if(element.length == 1 || element.isEmpty()){
                _mainText.value = ""
            }else {
                _mainText.value = element.dropLast(1)
            }
        }else{
            _mainText.value = ""
        }
    }

    private fun getActions(){
        viewModelScope.launch {
            _actions = GetListOfActionsUseCase(actionsRepository).execute() as MutableLiveData<List<Action>>
        }
    }

    fun addAction(mainAction : String, result : String){
        viewModelScope.launch {
            val id = CreateIdForActionItem(idRepository).execute()
            val action = Action(id, mainAction, result)
            AddActionToListUseCase(actionsRepository).execute(action)
        }
    }

    fun deleteHistory(){
        viewModelScope.launch {
            DeleteAllItemsFromListUseCase(actionsRepository).execute()
        }
    }

}