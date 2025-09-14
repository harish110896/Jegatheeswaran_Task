package com.jegatheeswaran.task.ui.screens.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.data.model.HoldingDto
import com.jegatheeswaran.task.data.model.calculatePnl
import com.jegatheeswaran.task.ui.theme.GREEN50
import com.jegatheeswaran.task.ui.theme.Gray45
import com.jegatheeswaran.task.ui.theme.Gray48
import com.jegatheeswaran.task.ui.theme.Gray70
import com.jegatheeswaran.task.ui.theme.RED50
import com.jegatheeswaran.task.utils.toRupeeString

@Composable
fun HoldingItem(item: HoldingDto) {

    Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().background(color = Color.White)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            val (stockName, netQtyHeader, netQtyValue, ltpHeader, ltpValue, pnlHeader, pnlValue) = createRefs()

            Text(
                text = item.symbol,
                color = Gray45,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(stockName) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.wrapContent
                })

            Text(
                text = stringResource(R.string.net_qty_header),
                color = Gray70,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(netQtyHeader) {
                    start.linkTo(parent.start)
                    top.linkTo(netQtyValue.top)
                    bottom.linkTo(netQtyValue.bottom)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })

            Text(
                text = item.quantity.toString(),
                color = Gray48,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(netQtyValue) {
                    top.linkTo(stockName.bottom, margin = 24.dp)
                    start.linkTo(netQtyHeader.end, margin = 2.dp)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
            )

            Text(
                text = item.ltp.toRupeeString(),
                color = Gray48,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(ltpValue) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })

            Text(
                text = stringResource(R.string.ltp),
                color = Gray70,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(ltpHeader) {
                    end.linkTo(ltpValue.start, margin = 2.dp)
                    bottom.linkTo(ltpValue.bottom)
                    top.linkTo(ltpValue.top)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })

            Text(
                text = item.calculatePnl().first,
                color = if(item.calculatePnl().second) GREEN50 else RED50,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(pnlValue) {
                    end.linkTo(parent.end)
                    top.linkTo(ltpValue.bottom, margin = 24.dp)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })

            Text(
                text = stringResource(R.string.pnl),
                color = Gray70,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(pnlHeader) {
                    bottom.linkTo(pnlValue.bottom)
                    top.linkTo(pnlValue.top)
                    end.linkTo(pnlValue.start, margin = 2.dp)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                })
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = Dp.Hairline,
            color = Color.DarkGray
        )
    }

}
