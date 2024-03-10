package com.example.truthordarecompose.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.truthordarecompose.data.local.PlayersDao
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import com.example.truthordarecompose.domain.usecases.app_entry.ReadTypeMode
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel@Inject constructor(
    private val playersDao: PlayersDao,
    readTypeMode: ReadTypeMode
): ViewModel() {

    private val _allPlayers = MutableStateFlow<List<PlayerEntity>>(emptyList())
    val randomPlayer: StateFlow<List<PlayerEntity>> =_allPlayers

    private val _typeMode = MutableStateFlow("")
    val allPlayers: StateFlow<String> = _typeMode

    init{

        viewModelScope.launch {
            playersDao.getPlayers().collect { users ->
                _allPlayers.value = users
            }
        }
        readTypeMode().onEach { typeModeTemp ->
            when(typeModeTemp){
                TypeMode.CHILDREN->_typeMode.value="Детский"
                TypeMode.TEENAGERS->_typeMode.value="Подростковый"
                TypeMode.ADULTS->_typeMode.value="Взрослый"
                TypeMode.PAIR->_typeMode.value="Пара"
                else -> {_typeMode.value="Детский"}
            }
        }.launchIn(viewModelScope)
    }
}