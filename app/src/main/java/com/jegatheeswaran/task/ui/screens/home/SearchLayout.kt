package com.jegatheeswaran.task.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import com.jegatheeswaran.task.ui.screens.holding.HoldingMainViewModel
import com.jegatheeswaran.task.utils.HOLDING_TAB
import com.jegatheeswaran.task.utils.POSITION_TAB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@Composable
fun SearchLayout(isAppBarVisible: MutableState<Boolean>, viewModel: HoldingMainViewModel, activeTab: Int) {
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    BackHandler(isAppBarVisible.value.not()) {
        isAppBarVisible.value = true
    }

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                disabledLabelColor = Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                text = it
                when (activeTab) {
                    POSITION_TAB -> {
                        //Show error snack bar
                    }
                    HOLDING_TAB -> viewModel.searchHolding(it)
                }
            },
            singleLine = true,
            trailingIcon = {
                if (text.trim().isNotEmpty()) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                            .clickable { text = "" }
                    )
                } else {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                    )
                }
            }
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}