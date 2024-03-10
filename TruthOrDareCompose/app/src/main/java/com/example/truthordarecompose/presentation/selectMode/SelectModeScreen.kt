package com.example.truthordarecompose.presentation.selectMode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarecompose.R
import com.example.truthordarecompose.presentation.selectMode.components.ButtonMode
import com.example.truthordarecompose.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectModeScreen(onAddPlayersClicked: () -> Unit,kidsClicked: () -> Unit,teenagersClicked: () -> Unit,adultsClicked: () -> Unit,pairClicked: () -> Unit,) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onAddPlayersClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_people),
                            contentDescription = "Назад",
                            tint = Pink
                        )
                    }
                },
                title = {

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
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Выберите режим",
                    style = TextStyle(color = Pink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                ButtonMode(textMode = "Детский", icon = R.drawable.children) {
                    kidsClicked()
                }

                ButtonMode(textMode = "Подростковый", icon = R.drawable.children) {
                    teenagersClicked()
                }

                ButtonMode(textMode = "Взрослый", icon = R.drawable.children) {
                    adultsClicked()
                }

                ButtonMode(textMode = "Пара", icon = R.drawable.children) {
                    pairClicked()
                }
            }
        }
    )
}