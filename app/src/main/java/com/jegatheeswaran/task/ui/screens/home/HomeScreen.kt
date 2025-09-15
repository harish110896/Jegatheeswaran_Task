package com.jegatheeswaran.task.ui.screens.home

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.navigation.NavGraph
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.ui.common.CircularIndeterminateProgressBar
import com.jegatheeswaran.task.ui.common.CustomTopBar
import com.jegatheeswaran.task.ui.screens.portfolio.HoldingMainViewModel
import com.jegatheeswaran.task.ui.screens.portfolio.PortfolioSummaryLayout
import com.jegatheeswaran.task.ui.screens.portfolio.TabLayout
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.TAB_COUNT
import com.jegatheeswaran.task.utils.network.ConnectionState
import com.jegatheeswaran.task.utils.network.connectivityState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val navController = rememberNavController()
    val topBarVisible = remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(initialPage = HOLDING_TAB) { TAB_COUNT }
    Scaffold(
        topBar = {
            if (!topBarVisible.value) {
                //we can use for search in future
            } else {
                CustomTopBar(
                    navigationTitle(navController),
                    onLeftIconClick = {

                    },
                    onRightIcon1Click = {}
                ) {

                }
            }
        },
        bottomBar = {
            BottomBarLayout(navController)
        }
    ) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            MainView(
                navController = navController,
                pagerState = pagerState
            )
        }
    }

}

@Composable
fun MainView(
    navController: NavHostController,
    pagerState: PagerState
) {
    val viewModel = hiltViewModel<HoldingMainViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    val connection by connectivityState()
    val isConnected = connection == ConnectionState.Available
    LaunchedEffect(Unit) {
        viewModel.loadHolding(isConnected)
    }
    //todo handle back
    BackHandler(enabled = true) {

    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        val (tabLayout, pager, bottomLayout, progressBar) = createRefs()
        TabLayout(
            navController, pagerState, modifier = Modifier
                .constrainAs(tabLayout) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth())
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(pager) {
                    top.linkTo(tabLayout.bottom)
                    start.linkTo(parent.start)
                },
            userScrollEnabled = false
        ) {
            NavGraph(navController)
        }
        AnimatedVisibility(
            visible = pagerState.currentPage == HOLDING_TAB, modifier = Modifier
                .padding(top = 0.dp)
                .constrainAs(bottomLayout) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }) {
            PortfolioSummaryLayout(items = uiState.holdingResults)
        }
        CircularIndeterminateProgressBar(
            modifier = Modifier
                .padding()
                .fillMaxSize()
                .constrainAs(progressBar) {
                    top.linkTo(pager.top)
                    start.linkTo(pager.start)
                }, isDisplayed = uiState.isLoading, 0.1f
        )
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        //Can be used to expand for other screens in the bottom bar
        ScreenName.Portfolio.route -> stringResource(id = R.string.portfolio)
        else -> {
            //Default Portfolio for now
            stringResource(R.string.portfolio)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}