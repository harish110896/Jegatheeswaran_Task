package com.jegatheeswaran.task.ui.screens.holding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jegatheeswaran.task.data.model.HoldingDto
import com.jegatheeswaran.task.ui.theme.Blue40

@Composable
fun HoldingItem(item: HoldingDto) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        val (stockName, netQtyHeader, netQtyValue, ltpHeader, ltpValue, pnlHeader, pnlValue) = createRefs()

        Text(
            text = item.symbol,
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(stockName) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                width = Dimension.wrapContent
            })

        Text(
            text = "NET QTY : ",
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(netQtyHeader) {
                start.linkTo(parent.start)
                top.linkTo(stockName.bottom, margin = 12.dp)
                width = Dimension.wrapContent
            })

        Text(
            text = item.quantity.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(netQtyValue) {
                start.linkTo(netQtyHeader.end, margin = 2.dp)
                bottom.linkTo(netQtyHeader.bottom)
                width = Dimension.wrapContent
            }
        )

        Text(
            text = item.ltp.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(ltpValue) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            })

        Text(
            text = "LTP : ",
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(ltpHeader) {
                top.linkTo(parent.top)
                end.linkTo(ltpValue.start, margin = 2.dp)
                width = Dimension.wrapContent
            })

        Text(
            text = item.avgPrice.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(pnlValue) {
                top.linkTo(ltpValue.bottom, margin = 12.dp)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            })

        Text(
            text = "P&L : ",
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(pnlHeader) {
                bottom.linkTo(pnlValue.bottom)
                end.linkTo(pnlValue.start, margin = 2.dp)
                width = Dimension.wrapContent
            })
    }
}
