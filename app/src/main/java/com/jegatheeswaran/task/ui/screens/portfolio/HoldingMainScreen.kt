package com.jegatheeswaran.task.ui.screens.portfolio

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.utils.network.ConnectionState
import com.jegatheeswaran.task.utils.network.connectivityState


@Composable
fun HoldingMainScreen() {

    val viewModel = hiltViewModel<HoldingMainViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val connection by connectivityState()
    val isConnected = connection == ConnectionState.Available
    LaunchedEffect(Unit) {
        viewModel.loadHolding(isConnected)
    }
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()) {
        val (holdingList, progressBar ) = createRefs()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight()
                .background(color = Color.White)
                .padding(top = 8.dp).constrainAs(holdingList)
            {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                height = Dimension.fillToConstraints
            }
        ) {
            items(items = uiState.holdingResults, itemContent = { item ->
                HoldingItem(item)
            })
        }
    }

}