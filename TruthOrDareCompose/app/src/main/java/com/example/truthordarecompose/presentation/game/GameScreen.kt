package com.example.truthordarecompose.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import com.example.truthordarecompose.ui.theme.DarkPink
import com.example.truthordarecompose.ui.theme.Pink
import java.util.Locale
import java.util.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(onSelectedModeClicked: () -> Unit,nameCallback: (String) -> Unit, isDareSelectedMode: ((Boolean) -> Unit)? = null) {
    val viewModel: GameViewModel = hiltViewModel()
    val typeModeState by viewModel.allPlayers.collectAsState()
    var name by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onSelectedModeClicked) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Назад",
                            tint = Pink
                        )
                    }
                },
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = name,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = DarkPink,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        LaunchedEffect(viewModel.randomPlayer) {
                            viewModel.randomPlayer.collect { playersState ->
                                if (playersState.isNotEmpty()) {
                                    val randomPlayer = chooseRandomPlayer(playersState)
                                    name = randomPlayer.name
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(1.dp)
                                .background(Color.White)
                        )
                        Text(
                            text = typeModeState,
                            style = TextStyle(
                                color = DarkPink,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .systemBarsPadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Правда".uppercase(Locale.ROOT),
                    style = TextStyle(color = Pink, fontSize = 40.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable {
                        nameCallback.invoke(name)
                        isDareSelectedMode?.invoke(false)

                    }
                )
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(50.dp)
                        .height(1.dp)
                        .background(Color.White)

                )
                Text(
                    text = "Случайный выбор".uppercase(Locale.ROOT),
                    style = TextStyle(
                        color = DarkPink,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable {
                        val randomBoolean: Boolean = Random().nextBoolean()
                        nameCallback.invoke(name)
                        isDareSelectedMode?.invoke(randomBoolean)
                    }
                )
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(50.dp)
                        .height(1.dp)
                        .background(Color.White)

                )
                Text(
                    text = "Действие".uppercase(Locale.ROOT),
                    style = TextStyle(color = Pink, fontSize = 40.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable {
                        nameCallback.invoke(name)
                        isDareSelectedMode?.invoke(true)
                    }
                )
            }
        }
    )
}
fun chooseRandomPlayer(playersState: List<PlayerEntity>): PlayerEntity {
    val selectedPlayers = mutableSetOf<String>()
    val availablePlayers = playersState.filterNot { it.name in selectedPlayers }
    val randomPlayer = availablePlayers.random()
    selectedPlayers.add(randomPlayer.name)
    return randomPlayer
}