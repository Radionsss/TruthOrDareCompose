package com.example.truthordarecompose.presentation.game

sealed class GameEvent {
    data class UpdateIsDareIntent(val isDare: Boolean) : GameEvent()
}