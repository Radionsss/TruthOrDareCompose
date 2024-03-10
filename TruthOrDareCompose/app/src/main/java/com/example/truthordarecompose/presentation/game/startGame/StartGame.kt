package com.example.truthordarecompose.presentation.game.startGame

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
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
import com.example.truthordarecompose.R
import com.example.truthordarecompose.presentation.addPlayers.components.RoundButton
import com.example.truthordarecompose.presentation.game.GameViewModel
import com.example.truthordarecompose.ui.theme.DarkPink
import com.example.truthordarecompose.ui.theme.Pink
import java.util.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartGame(
    onSelectedModeClicked: () -> Unit,
    onCompleteClicked: () -> Unit,
    isDare: Boolean,
    context: Context,
    nameParam: String
) {
    val viewModel: GameViewModel = hiltViewModel()
         val typeModeState by viewModel.allPlayers.collectAsState()
    var randomElement by remember {
        mutableStateOf("")
    }

    LaunchedEffect(viewModel.allPlayers) {
        randomElement=handleNameAndDare(isDare, context, viewModel)
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
                            text = nameParam,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = DarkPink,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
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
                Spacer(modifier = Modifier.weight(1f)) // Пустое пространство, чтобы выровнять внизу

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(50.dp)
                        .height(1.dp)
                        .background(Pink)

                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = randomElement,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(50.dp)
                        .height(1.dp)
                        .background(Pink)
                )
                Spacer(modifier = Modifier.weight(1f)) // Пустое пространство, чтобы выровнять внизу

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .systemBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    RoundButton(Icons.Default.Refresh) {
                        randomElement=handleNameAndDare(isDare, context, viewModel)
                    }
                    RoundButton(Icons.Default.Done) {
                        onCompleteClicked()
                    }
                }
            }
        }
    )
}
private fun handleNameAndDare(isDare: Boolean, context: Context,viewModel:GameViewModel): String {
    val paramTypeMode= viewModel.allPlayers.value

    val newStringArray = if (isDare) {
        Log.d("nadwawdadwadwadwadawme", paramTypeMode+ "$$$$$$$$$$$$$$$$$$$")
        when (paramTypeMode) {
            "Детский" -> context.resources.getStringArray(R.array.dare_child)
            "Подростковый" -> context.resources.getStringArray(R.array.dare_teenagers)
            "Взрослый" -> context.resources.getStringArray(R.array.dare_adult)
            "Пара" -> context.resources.getStringArray(R.array.dare_pair)
            else -> emptyArray()
        }
    } else {
        Log.d("nadwawdadwadwadwadawme", paramTypeMode+ "$$$$$$$$$$$$$$$$$$$")
        when (paramTypeMode) {

            "Детский" -> context.resources.getStringArray(R.array.truth_child)
            "Подростковый" -> context.resources.getStringArray(R.array.truth_teenagers)
            "Взрослый" -> context.resources.getStringArray(R.array.truth_adult)
            "Пара" -> context.resources.getStringArray(R.array.truth_pair)
            else -> emptyArray()
        }
    }

    return if (newStringArray.isNotEmpty()) {
        val randomIndex = Random().nextInt(newStringArray.size)
        newStringArray[randomIndex]
    } else {
        "11111111111111111111111111111111111111111111"
    }
}