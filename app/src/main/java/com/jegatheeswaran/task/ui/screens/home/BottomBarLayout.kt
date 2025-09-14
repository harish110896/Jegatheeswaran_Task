package com.jegatheeswaran.task.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.ui.theme.Blue40

@Composable
fun BottomBarLayout(navController: NavController) {
    val context = LocalContext.current
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
                icon = {
                    Icon(
                        painterResource(item.navIcon),
                        contentDescription = "Bottom Nav Icon",
                        tint = if (isDefaultSelected(item)) Blue40 else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        color = if (isDefaultSelected(item)) Blue40 else Color.Gray
                    )
                },
                selected = isDefaultSelected(item),
                onClick = {
                    if(!isDefaultSelected(item)) {
                        Toast.makeText(context,  "Screen not available", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}

private fun RowScope.isDefaultSelected(item: ScreenName): Boolean {
    return ScreenName.Portfolio.route == item.route
}
