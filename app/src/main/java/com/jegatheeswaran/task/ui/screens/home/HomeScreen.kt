package com.jegatheeswaran.task.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.navigation.NavGraph
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.ui.screens.BottomBarLayout
import com.jegatheeswaran.task.ui.screens.CustomTopBar
import com.jegatheeswaran.task.ui.screens.TabLayout
import com.jegatheeswaran.task.ui.screens.holding.HoldingMainViewModel
import com.jegatheeswaran.task.ui.theme.Gray40
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.POSITION_TAB
import com.jegatheeswaran.task.utils.TAB_COUNT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val viewModel = hiltViewModel<HoldingMainViewModel>()
    val navController = rememberNavController()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(initialPage = HOLDING_TAB) { TAB_COUNT }
    Scaffold(
        topBar = {
            if (!isAppBarVisible.value) {
                SearchLayout(isAppBarVisible, viewModel, pagerState.currentPage)
            } else {
                CustomTopBar(
                    stringResource(R.string.portfolio),
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
                .fillMaxHeight()
                .background(color = Gray40)
        ) {
            MainView(
                navController = navController,
                pagerState = pagerState
            )
//            CircularIndeterminateProgressBar(isDisplayed = uiState.isLoading, 0.1f)

            if (!isAppBarVisible.value) {
                val results = when (pagerState.currentPage) {
                    POSITION_TAB -> {

                    }

                    HOLDING_TAB -> {

                    }

                    else -> null
                }
                results?.let {
//                    SearchScreen(navController, it) {
//                        isAppBarVisible.value = true
//                    }
                }
            }
        }
    }

}

@Composable
fun MainView(
    navController: NavHostController,
    pagerState: PagerState
) {
    //todo handle back
//    BackHandler(enabled = currentRoute in backDialogRoutes) {
//        openDialog.value = true
//    }
    Column {
        TabLayout(navController, pagerState)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) {
            NavGraph(navController)
        }
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