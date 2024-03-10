package com.example.truthordarecompose.presentation.selectMode

import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode

sealed class SelectModeEvent() {

    object SaveModeEvent: SelectModeEvent()

}