package com.jegatheeswaran.task.ui.screens

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.navigation.currentRoute
import com.jegatheeswaran.task.utils.singleTopNavigator

@Composable
fun BottomBarLayout(navController: NavController) {
    NavigationBar {
        val items = listOf(
            ScreenName.Watchlist,
            ScreenName.Orders,
            ScreenName.Portfolio,
            ScreenName.Funds,
            ScreenName.Invest
        )

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = item.navIcon,
                label = { Text(text = stringResource(id = item.title)) },
                selected = currentRoute(navController) == item.route,
                onClick = {
                    navController.singleTopNavigator(item.route)
                })
        }
    }
}
