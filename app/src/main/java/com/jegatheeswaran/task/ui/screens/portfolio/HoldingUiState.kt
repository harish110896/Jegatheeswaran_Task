package com.jegatheeswaran.task.ui.screens.portfolio

import com.jegatheeswaran.task.data.model.HoldingDto

data class HoldingUiState(
    val holdingResults: List<HoldingDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)