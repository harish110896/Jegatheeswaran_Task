package com.jegatheeswaran.task.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.POSITION_TAB
import com.jegatheeswaran.task.utils.singleTopNavigator
import kotlinx.coroutines.launch

@Composable
fun TabLayout(
    navigator: NavHostController,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(stringResource(R.string.portfolio_tab), stringResource(R.string.holding_tab))
    TabRow(modifier = Modifier.background(Color.White),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.PrimaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = MaterialTheme.colorScheme.primary
            )
        }) {
        tabs.forEachIndexed { index, title ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                when (index) {
                    POSITION_TAB -> {
                        navigator.singleTopNavigator(ScreenName.Position.route)

                    }
                    HOLDING_TAB -> {
                        navigator.singleTopNavigator(ScreenName.Holding.route)
                    }
                }
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }, text = {
                Text(
                    title,
                    color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else Color.Gray
                )
            })
        }
    }
}