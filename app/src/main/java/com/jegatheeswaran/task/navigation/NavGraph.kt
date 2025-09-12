package com.jegatheeswaran.task.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.ui.screens.holding.HoldingMainScreen
import com.jegatheeswaran.task.ui.screens.holding.PositionScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = ScreenName.Holding.route) {
        composable (ScreenName.Position.route){
            PositionScreen()
        }
        composable(ScreenName.Holding.route){
            HoldingMainScreen()
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        ScreenName.Portfolio.route -> stringResource(id = R.string.portfolio)
        else -> {
            stringResource(R.string.app_name)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
