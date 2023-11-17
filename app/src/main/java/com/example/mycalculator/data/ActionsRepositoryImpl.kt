package com.example.mycalculator.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycalculator.domain.ActionsRepository
import com.example.mycalculator.domain.helpers.models.Action

class ActionsRepositoryImpl : ActionsRepository {


    companion object {
        const val TAG = "ActionsRepositoryImpl"
    }


    private val _list : MutableLiveData<MutableList<Action>> = MutableLiveData<MutableList<Action>>()
    private val list : MutableLiveData<List<Action>> = MutableLiveData<List<Action>>()

    init {
        _list.value = mutableListOf()
    }

    override suspend fun getList(): LiveData<List<Action>> {
        return list
    }

    override suspend fun addItem(item: Action) {
        try {
            if(_list.value != null) {
                _list.value!!.add(item)
                refreshList()
                Log.d(TAG,"addItem : Success = (item = $item) /n (list after = ${list.value})")
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"addItem : Error = $e")
        }
    }

    override suspend fun deleteItem(id: String) {
        try {
            val item = getItemById(id)
            if(_list.value != null) {
                _list.value!!.remove(item)
                refreshList()
                Log.d(TAG,"deleteItem : Success = (item = $item) /n (list after = ${list.value})")
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"deleteItem : Error = $e")
        }
    }

    override suspend fun deleteAllItems() {
        try {
            if(_list.value != null) {
                _list.value!!.clear()
                refreshList()
                Log.d(TAG,"deleteAllItems : Success /n (list after = ${list.value})")
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"deleteAllItems : Error = $e")
        }
    }

    override suspend fun getItemById(idOld: String): Action? {
        try {
            if(_list.value != null) {
                for (elem in _list.value!!){
                    if(elem.idItem == idOld){
                        Log.d(TAG,"getItemById : Success = (item = $elem) /n (list after = ${list.value})")
                        return elem
                    }
                }
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"getItemById : Error = $e")
        }
        return null
    }

    override suspend fun setList(listNew: List<Action>) {
        try {
            if(_list.value != null) {
                val oldList = _list.value
                _list.value!!.clear()
                _list.value!!.addAll(listNew)
                refreshList()
                Log.d(TAG,"setList : Success = (list until = $oldList) /n (list after = ${list})")
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"setList : Error = $e")
        }
    }

    private fun refreshList() {
        try {
            if(_list.value != null) {
//                val list = MutableLiveData<List<Action>>()
                list.value = _list.value
            }else{
                throw Exception("list is null")
            }
        }catch (e : Exception){
            Log.d(TAG,"setList : Error = $e")
        }
    }
}