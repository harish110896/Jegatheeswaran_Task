package com.jegatheeswaran.task.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.ui.screens.BottomBarLayout
import com.jegatheeswaran.task.ui.screens.CustomTopBar
import com.jegatheeswaran.task.ui.screens.holding.HoldingMainViewModel
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.POSITION_TAB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val viewModel = hiltViewModel<HoldingMainViewModel>()
    val navController = rememberNavController()
    val isAppBarVisible = remember { mutableStateOf(true) }
    val pagerState = rememberPagerState { HOLDING_TAB }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            if (!isAppBarVisible.value) {
                SearchLayout(isAppBarVisible, viewModel, pagerState.currentPage)
            } else {
//                TopAppBar(
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Blue40,
//                        titleContentColor = Color.White,
//                    ),
//                    title = {
//                        Text(
//                            text = navigationTitle(navController),
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis,
//                            color = Color.White
//                        )
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            Toast.makeText(
//                                context,
//                                context.getString(R.string.feature_not_available),
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }) {
//                            Icon(
//                                Icons.AutoMirrored.Filled.ArrowBack,
//                                contentDescription = null,
//                                tint = Color.White
//                            )
//                        }
//                    },
//                    actions = {
//                        IconButton(onClick = {
//                            Toast.makeText(
//                                context,
//                                context.getString(R.string.feature_not_available),
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }) {
//                            Icon(
//                                Icons.Filled.Search,
//                                contentDescription = null,
//                                tint = Color.Gray
//                            )
//                        }
//                    }
//                )
                CustomTopBar(stringResource(R.string.portfolio),
                    onLeftIconClick = {
                        Toast.makeText(context,"Not available", Toast.LENGTH_SHORT).show()
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
                .background(color = Color.White)
        ) {
//            MainView(
//                navController = navController,
//                pagerState = pagerState,
//                genres = uiState.genres?.genres,
//                isFavorite = isFavoriteActive.value
//            )
//            CircularIndeterminateProgressBar(isDisplayed = uiState.isLoading, 0.1f)

            if (!isAppBarVisible.value) {
                val results = when (pagerState.currentPage) {
                    POSITION_TAB -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.feature_not_available), Toast.LENGTH_SHORT
                        ).show()
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