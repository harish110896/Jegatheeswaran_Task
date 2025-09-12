package com.jegatheeswaran.task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jegatheeswaran.task.ui.theme.Blue40

@Composable
fun CustomTopBar(
    title: String,
    onLeftIconClick: () -> Unit,
    onRightIcon1Click: () -> Unit,
    onRightIcon2Click: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp).background(color = Blue40) // total height of top bar
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // push to bottom
                .padding(start = 12.dp, end = 12.dp, bottom = 6.dp)
                ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left icon
            IconButton(onClick = onLeftIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ManageSearch,
                    contentDescription = "Left Icon",
                    tint = Color.White
                )
            }

            // Title
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Right icons
            IconButton(onClick = onRightIcon2Click) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ManageSearch,
                    contentDescription = "Right Icon 2",
                    tint = Color.White
                )
            }

            IconButton(onClick = onRightIcon1Click) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ManageSearch,
                    contentDescription = "Right Icon 1",
                    tint = Color.White
                )
            }
        }
    }
}