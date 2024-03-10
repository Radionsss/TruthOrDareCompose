package com.example.truthordarecompose.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val argumentsDare: Boolean=false
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    data object StartGameScreen : Route(route = "startGameScreen/{argumentsDare}")

    object GameScreen : Route(route = "gameScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object SelectModeScreen : Route(route = "selectModeScreen")

    object AddPlayerNavigation : Route(route = "addPlayerNavigation")
    object AddPlayerNavigator : Route(route = "addPlayerNavigator")

}

