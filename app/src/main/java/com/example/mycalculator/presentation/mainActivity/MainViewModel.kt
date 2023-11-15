package com.example.mycalculator.presentation.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private var _mainText : MutableLiveData<String> = MutableLiveData<String>()
    val mainText: LiveData<String> get() = _mainText

    private var _showMainText : MutableLiveData<String> = MutableLiveData<String>()
    val showMainText: LiveData<String> get() = _showMainText

    init {
        _mainText.value = ""
        _showMainText.value = "0.0"
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

}