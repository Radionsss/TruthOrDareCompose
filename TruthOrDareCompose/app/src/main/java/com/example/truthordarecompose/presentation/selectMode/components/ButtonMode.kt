package com.example.truthordarecompose.presentation.selectMode.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.truthordarecompose.R
import com.example.truthordarecompose.ui.theme.Pink

@Composable
fun ButtonMode(textMode: String, icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 50.dp, start = 50.dp)
            .border(
                width = 2.dp, // ширина обводки
                color = Pink, // цвет обводки
                shape = RoundedCornerShape(16.dp) // радиус закругления углов
            )
            .clickable {
                onClick.invoke()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(30.dp),
            text = textMode,
            style = TextStyle(color = Pink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Icon(
            modifier = Modifier.padding(30.dp).size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonModePreview() {
    ButtonMode("Детский", R.drawable.ic_close, {})
}