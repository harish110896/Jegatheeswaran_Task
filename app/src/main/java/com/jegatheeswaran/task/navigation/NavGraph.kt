package com.jegatheeswaran.task.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.ui.screens.portfolio.HoldingMainScreen
import com.jegatheeswaran.task.ui.screens.portfolio.PositionScreen

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
