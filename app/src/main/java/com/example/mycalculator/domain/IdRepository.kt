package com.example.mycalculator.domain

import androidx.lifecycle.LiveData
import com.example.mycalculator.domain.helpers.models.Action

class IdRepository {

    @Volatile
    var actionId = "100"

    suspend fun idAction() : String{
        actionId = (actionId.toInt() + 1).toString()
        return actionId
    }

}