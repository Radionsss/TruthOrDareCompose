package com.example.truthordarecompose.presentation.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType

import androidx.navigation.navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.truthordarecompose.presentation.addPlayers.AddPlayersScreen
import com.example.truthordarecompose.presentation.addPlayers.AddPlayersViewModel
import com.example.truthordarecompose.presentation.game.GameScreen
import com.example.truthordarecompose.presentation.game.GameViewModel
import com.example.truthordarecompose.presentation.game.startGame.StartGame
import com.example.truthordarecompose.presentation.onboarding.OnBoardingScreen
import com.example.truthordarecompose.presentation.onboarding.OnBoardingViewModel
import com.example.truthordarecompose.presentation.selectMode.SelectModeEvent
import com.example.truthordarecompose.presentation.selectMode.SelectModeScreen
import com.example.truthordarecompose.presentation.selectMode.SelectModeViewModel
import com.example.truthordarecompose.presentation.selectMode.typeMode.TypeMode


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.AddPlayerNavigator.route,
            startDestination = Route.AddPlayerNavigation.route
        ) {
            composable(route = Route.AddPlayerNavigation.route) {
                val viewModel: AddPlayersViewModel = hiltViewModel()
                val playersState by viewModel.allPlayers.collectAsState()

                AddPlayersScreen(players = playersState) {
                    navController.navigate(Route.SelectModeScreen.route)
                }
            }
        }

        composable(route = Route.SelectModeScreen.route) {
            val viewModel: SelectModeViewModel = hiltViewModel()

            SelectModeScreen(
                onAddPlayersClicked = {
                    navController.navigate(Route.AddPlayerNavigator.route)
                },
                kidsClicked = {
                    viewModel.onEvent(SelectModeEvent.SaveModeEvent, TypeMode.CHILDREN)
                    navController.navigate(Route.GameScreen.route)
                },
                teenagersClicked = {
                    viewModel.onEvent(
                        SelectModeEvent.SaveModeEvent,
                        TypeMode.TEENAGERS
                    )
                    navController.navigate(Route.GameScreen.route)
                },
                adultsClicked = {
                    viewModel.onEvent(
                        SelectModeEvent.SaveModeEvent,
                        TypeMode.ADULTS
                    )
                    navController.navigate(Route.GameScreen.route)
                },
                pairClicked = {
                    viewModel.onEvent(
                        SelectModeEvent.SaveModeEvent,
                        TypeMode.PAIR
                    )
                    navController.navigate(Route.GameScreen.route)
                }
            )
        }
        composable(route = Route.GameScreen.route) {
            val viewModel: GameViewModel = hiltViewModel()
            /*   AnimatedVisibility(
                   visible = true,
                   enter = slideInHorizontally(initialOffsetX = { it }),
                   exit = slideOutHorizontally(targetOffsetX = { -it })
               ) {*/
            var name by rememberSaveable { mutableStateOf("he") }
            GameScreen(
                onSelectedModeClicked = {
                    navController.navigate(Route.SelectModeScreen.route)
                },
                isDareSelectedMode = {
                    navController.navigate("startGameScreen/${it}/${name}")
                },
                nameCallback = {
                    name=it
                },
            )
            // }
        }
        composable(
            route = "startGameScreen/{argumentsDare}/{name}",
            arguments = listOf(
                navArgument("argumentsDare") { type = NavType.BoolType },
                navArgument("name") { type = NavType.StringType })
            ) {
            val paramDare = it.arguments?.getBoolean("argumentsDare") ?: false
            val nameParam = it.arguments?.getString("name") ?: "false"
            val context = LocalContext.current
            /*   AnimatedVisibility(
                   visible = true,
                   enter = slideInHorizontally(initialOffsetX = { i->i }),
                   exit = slideOutHorizontally(targetOffsetX = {i->-i })
               ) {*/
            StartGame(
                onSelectedModeClicked = {
                    navController.navigate(Route.GameScreen.route)
                },
                onCompleteClicked = {
                    navController.navigate(Route.GameScreen.route)
                },
                isDare = paramDare,
                context,nameParam
            )
            //}
        }
    }
}