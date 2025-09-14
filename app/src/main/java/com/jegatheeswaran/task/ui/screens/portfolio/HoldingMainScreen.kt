package com.jegatheeswaran.task.ui.screens.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.jegatheeswaran.task.ui.common.CircularIndeterminateProgressBar

@Composable
fun HoldingMainScreen() {

    val viewModel = hiltViewModel<HoldingMainViewModel>()
    val uiState by viewModel.uiState.collectAsState()
//    LaunchedEffect(Unit) {
//        viewModel.loadHolding()
//    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (holdingList, progressBar, summary) = createRefs()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
                .padding(top = 8.dp)
                .constrainAs(holdingList) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }) {
            items(items = uiState.holdingResults, itemContent = { item ->
                HoldingItem(item)
            })
        }
        //        Box(
//            Modifier
//                .padding(top = 0.dp).constrainAs(summary) {
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                }
//        ){
//            PortfolioSummaryLayout(items = uiState.holdingResults)
//        }
        CircularIndeterminateProgressBar(
            modifier = Modifier
                .padding()
                .fillMaxSize()
                .constrainAs(progressBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }, isDisplayed = uiState.isLoading, 0.1f
        )

    }
}