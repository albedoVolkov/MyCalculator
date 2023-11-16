package com.example.mycalculator.domain

import com.example.mycalculator.domain.helpers.operationsEnum.basedClasses.Buttons

interface FromTaskActivityToMainActivity {
    fun communicate(action : Buttons)
}