package com.example.mycalculator.presentation

import com.example.mycalculator.domain.helpers.Buttons

interface FromTaskActivityToMainActivity {
    fun communicate(action : Buttons)
}