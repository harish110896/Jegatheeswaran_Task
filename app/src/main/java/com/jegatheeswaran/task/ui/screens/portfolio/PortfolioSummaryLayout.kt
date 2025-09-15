package com.jegatheeswaran.task.ui.screens.portfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.data.model.HoldingDto
import com.jegatheeswaran.task.data.model.calculateCurrentValue
import com.jegatheeswaran.task.data.model.calculateTodayPnl
import com.jegatheeswaran.task.data.model.calculateTotalInvestment
import com.jegatheeswaran.task.data.model.calculateTotalPnl
import com.jegatheeswaran.task.ui.common.HeaderValueLayout
import com.jegatheeswaran.task.ui.theme.Gray95

@Composable
fun PortfolioSummaryLayout(
    items: List<HoldingDto>
) {
    var summaryVisible by rememberSaveable { mutableStateOf(false) }
    AnimatedVisibility(visible = items.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = Dp.Hairline,
                    color = Color.Gray,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(color = Gray95)
        ) {
            AnimatedVisibility(visible = summaryVisible) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HeaderValueLayout(
                        headerText = stringResource(R.string.current_value_header),
                        valueText = items.calculateCurrentValue().first
                    )
                    HeaderValueLayout(
                        headerText = stringResource(R.string.total_investment_header),
                        valueText = items.calculateTotalInvestment().first
                    )
                    HeaderValueLayout(
                        headerText = stringResource(R.string.today_s_profit_loss_header),
                        valueText = items.calculateTotalPnl().first,
                        colorByValue = true,
                        positiveValue = items.calculateTotalPnl().second

                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp),
                        thickness = Dp.Hairline,
                        color = Color.DarkGray
                    )
                }
            }
            HeaderValueLayout(
                headerText = stringResource(R.string.profile_loss_header),
                onHeaderClick = {
                    summaryVisible = !summaryVisible
                },
                valueText = items.calculateTodayPnl().first,
                headerDefaultIconResource = R.drawable.arrow_up_24px,
                headerToggleIconResource = R.drawable.arrow_down_24px,
                colorByValue = true,
                positiveValue = items.calculateTodayPnl().second,
            )
        }
    }

}


