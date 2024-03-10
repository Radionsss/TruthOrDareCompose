package com.example.truthordarecompose.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.truthordarecompose.data.local.PlayersDao
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import com.example.truthordarecompose.domain.usecases.app_entry.ReadAppEntry
import com.example.truthordarecompose.presentation.navgraph.Route

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    readAppEntry: ReadAppEntry,
    private val playersDao: PlayersDao
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AddPlayerNavigator.route)
    val startDestination: State<String> = _startDestination

    init {
        readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                _startDestination.value = Route.AddPlayerNavigator.route
            } else {
                insertPlayer()
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(300)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

    private fun insertPlayer() {
        viewModelScope.launch {
            playersDao.insert(PlayerEntity(name = "Player1"))
            playersDao.insert(PlayerEntity(name = "Player2", genderMan = false))
        }
    }
}




