package com.jegatheeswaran.task.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.jegatheeswaran.task.R

sealed class ScreenName(
    val route: String,
    @get:StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    data object Portfolio : ScreenName("portfolio", title = R.string.portfolio)
    data object Watchlist : ScreenName("watchlist", title = R.string.watch_list)
    data object Orders : ScreenName("orders", title = R.string.orders)
    data object Funds : ScreenName("funds", title = R.string.funds)
    data object Invest : ScreenName("invest", title = R.string.invest)

    data object Position : ScreenName("position")
    data object Holding : ScreenName("holding")
}