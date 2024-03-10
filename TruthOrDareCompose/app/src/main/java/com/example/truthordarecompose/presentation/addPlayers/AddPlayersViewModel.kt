package com.example.truthordarecompose.presentation.addPlayers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.truthordarecompose.data.local.PlayersDao
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlayersViewModel @Inject constructor(
    private val playersDao: PlayersDao
) : ViewModel() {
    private val _allPlayers = MutableStateFlow<List<PlayerEntity>>(emptyList())
    val allPlayers: StateFlow<List<PlayerEntity>> = _allPlayers

  init{
        viewModelScope.launch {
            playersDao.getPlayers().collect { users ->
                _allPlayers.value = users
            }
        }
    }

    fun insertPlayer() {
        viewModelScope.launch {
            playersDao.insert(PlayerEntity(name = ""))
        }
    }
    fun updatePlayer(id:Int,newName:String) {
        viewModelScope.launch {
            playersDao.updatePlayerText(id, newName )
        }
    }
    fun updatePlayerGender(id:Int,newGender:Boolean) {
        viewModelScope.launch {
            playersDao.updatePlayerGender(id, newGender )
        }
    }
    fun deletePlayer(id: Int) {
        viewModelScope.launch {
            playersDao.delete(id)
        }
    }
}