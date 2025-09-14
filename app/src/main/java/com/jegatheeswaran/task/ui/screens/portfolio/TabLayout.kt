package com.jegatheeswaran.task.ui.screens.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.navigation.ScreenName
import com.jegatheeswaran.task.ui.theme.Gray40
import com.jegatheeswaran.task.ui.theme.Gray60
import com.jegatheeswaran.task.ui.theme.Gray75
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.POSITION_TAB
import com.jegatheeswaran.task.utils.singleTopNavigator
import kotlinx.coroutines.launch

@Composable
fun TabLayout(
    navigator: NavHostController,
    pagerState: PagerState,
    modifier: Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(stringResource(R.string.positions_tab), stringResource(R.string.holding_tab))
    TabRow(
        modifier = modifier.background(Color.White),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .padding(bottom = 6.dp)
                    .height(1.dp)
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = Gray75
            )
        }) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                modifier = Modifier.height(48.dp),
                onClick = {
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
                },
                text = {
                    Text(
                        text = title,
                        color = if (pagerState.currentPage == index) Gray40 else Gray60
                    )
                })
        }
    }
}