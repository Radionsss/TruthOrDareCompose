package com.example.truthordarecompose.presentation.selectMode

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.truthordarecompose.domain.usecases.app_entry.SaveTypeMode
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectModeViewModel @Inject constructor(
    private val typeMode: SaveTypeMode,
) : ViewModel() {
    fun onEvent(event: SelectModeEvent,typeMode: TypeMode){
        when(event){
            is SelectModeEvent.SaveModeEvent ->{
                saveTypeMode(typeMode)
            }
        }
    }

    private fun saveTypeMode(typeMode: TypeMode) {
        viewModelScope.launch {
            typeMode(typeMode)
        }
    }

}