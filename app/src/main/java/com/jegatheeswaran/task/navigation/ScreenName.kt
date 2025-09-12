package com.jegatheeswaran.task.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jegatheeswaran.task.R

sealed class ScreenName(
    val route: String,
    @get:StringRes val title: Int = R.string.app_name,
    @get:DrawableRes val navIcon: Int = R.drawable.user_24px
) {
    data object Portfolio :
        ScreenName("portfolio", title = R.string.portfolio, navIcon = R.drawable.portfolio_24px)

    data object Watchlist :
        ScreenName("watchlist", title = R.string.watch_list, navIcon = R.drawable.watchlist_24px)

    data object Orders :
        ScreenName("orders", title = R.string.orders, navIcon = R.drawable.orders_24px)

    data object Funds : ScreenName("funds", title = R.string.funds, navIcon = R.drawable.funds_24px)
    data object Invest :
        ScreenName("invest", title = R.string.invest, navIcon = R.drawable.invest_24px)

    data object Position : ScreenName("position")
    data object Holding : ScreenName("holding")
}