package com.jegatheeswaran.task.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jegatheeswaran.task.R
import com.jegatheeswaran.task.ui.theme.Blue40

@Composable
fun CustomTopBar(
    title: String,
    onLeftIconClick: () -> Unit,
    onRightIcon1Click: () -> Unit,
    onRightIcon2Click: () -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(color = Blue40)
            .padding(horizontal = 6.dp, vertical = 6.dp)
    ) {
        val (leftIcon, titleText, rightIcon1, rightIcon2) = createRefs()

        IconButton(
            onClick = onLeftIconClick, modifier = Modifier.constrainAs(leftIcon) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }) {
            Icon(
                painterResource(R.drawable.user_24px),
                contentDescription = "Left Icon",
                tint = Color.White
            )
        }

        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(titleText) {
                start.linkTo(leftIcon.end, margin = 8.dp)
                end.linkTo(rightIcon2.start, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 14.dp)
                width = Dimension.fillToConstraints
            })

        IconButton(
            onClick = onRightIcon2Click, modifier = Modifier.constrainAs(rightIcon2) {
                end.linkTo(rightIcon1.start, margin = 8.dp)
                bottom.linkTo(parent.bottom)
            }) {
            Icon(
                painterResource(R.drawable.swap_24px),
                contentDescription = "Right Icon 2",
                tint = Color.White
            )
        }

        IconButton(
            onClick = onRightIcon1Click, modifier = Modifier.constrainAs(rightIcon1) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Icon(
                painterResource(R.drawable.search_24px),
                contentDescription = "Right Icon 1",
                tint = Color.White
            )
        }
    }
}