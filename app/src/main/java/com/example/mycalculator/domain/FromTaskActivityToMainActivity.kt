package com.example.mycalculator.domain

import com.example.mycalculator.domain.helpers.Buttons

interface FromTaskActivityToMainActivity {
    fun communicate(action : Buttons)
}