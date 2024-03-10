package com.example.truthordarecompose.presentation.addPlayers.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.truthordarecompose.R
import com.example.truthordarecompose.data.local.entities.PlayerEntity
import com.example.truthordarecompose.presentation.Dimens.MediumPadding1
import com.example.truthordarecompose.presentation.addPlayers.AddPlayersViewModel
import com.example.truthordarecompose.ui.theme.Pink
import com.example.truthordarecompose.ui.theme.TruthOrDareComposeTheme
import com.example.truthordarecompose.ui.theme.UnselectedColor
import com.example.truthordarecompose.ui.theme.White

@Composable
fun PlayerItem(player: PlayerEntity, onDelete: (PlayerEntity) -> Unit, changePlayerGender: (Boolean) -> Unit) {
    val viewModel: AddPlayersViewModel = hiltViewModel()
    val selectedGenderMan by rememberUpdatedState(newValue = player.genderMan)
    var text: String by remember { mutableStateOf( player.name) } // Initialize with the player's name
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Box(
            modifier = Modifier
                .width(4.dp) // Ширина полосы
                .height(30.dp)
                .background(Pink) // Цвет полосы
        )
        IconButton(onClick = {
            changePlayerGender.invoke(true)
        }) {
            Log.d("weweweweewewe", selectedGenderMan.toString())
            Icon(
                painter = painterResource(id = R.drawable.ic_man_selected),
                contentDescription = null,
                tint = if (selectedGenderMan) White else UnselectedColor
            )
        }
        IconButton(onClick = {
            changePlayerGender.invoke(false)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_woman_selected),
                contentDescription = null,
                tint = if (selectedGenderMan) UnselectedColor else White

            )
        }
        TextField(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .weight(1f),
            text = text,

            readOnly = false,
            onValueChange = {
                text = it
                player.id?.let { it1 -> viewModel.updatePlayer(it1, text) }
            },
        )
        IconButton(onClick = { onDelete.invoke(player)}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = White

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerItemPreview() {
    TruthOrDareComposeTheme(dynamicColor = false) {
        PlayerItem(
            player = PlayerEntity(
                name = "dwdd"
            ),
            {},
            {}
        )
    }
}