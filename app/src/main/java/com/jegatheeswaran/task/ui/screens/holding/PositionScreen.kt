package com.jegatheeswaran.task.ui.screens.holding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.jegatheeswaran.task.ui.theme.Blue40
import com.jegatheeswaran.task.ui.theme.Rose50
import com.jegatheeswaran.task.ui.theme.Teal60

@Composable
fun PositionScreen (){
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Screen is not currently available",
            color = Blue40,
            style = MaterialTheme.typography.titleMedium
        )
    }
}