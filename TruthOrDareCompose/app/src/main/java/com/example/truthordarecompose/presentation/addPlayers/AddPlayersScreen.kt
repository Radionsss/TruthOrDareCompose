package com.example.truthordarecompose.presentation.addPlayers

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import com.example.truthordarecompose.presentation.addPlayers.components.PlayerItem
import com.example.truthordarecompose.presentation.addPlayers.components.RoundButton
import com.example.truthordarecompose.ui.theme.Pink

@Composable
fun AddPlayersScreen(players: List<PlayerEntity>, onSelectModeClicked: () -> Unit) {
    val viewModel: AddPlayersViewModel = hiltViewModel()
    val playersState by viewModel.allPlayers.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Игроки",
            modifier = Modifier.padding(top = 20.dp),
            color = Pink,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Log.d("2wddwdwdwddsdssd", viewModel.allPlayers.value.toString())
            items(playersState) { player ->

                PlayerItem(
                    player = player,
                    onDelete = {
                        it.id?.let { it1 -> viewModel.deletePlayer(it1) }
                        Log.d("2wddwdsdssd", player.toString())
                    },
                    changePlayerGender = {
                        player.id?.let { it1 -> viewModel.updatePlayerGender(it1, it) }
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundButton(Icons.Default.Add) {
                viewModel.insertPlayer()
            }
            RoundButton(Icons.Default.Check) {
                onSelectModeClicked()
            }
        }

    }
}
