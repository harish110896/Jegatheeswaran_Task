package com.jegatheeswaran.task.ui.screens.holding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jegatheeswaran.task.data.remote.holding.HoldingRepositoryImpl
import com.jegatheeswaran.task.utils.network.ApiResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingMainViewModel @Inject constructor(
    private val holdingRepo: HoldingRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HoldingUiState())
    val uiState: StateFlow<HoldingUiState> get() = _uiState.asStateFlow()


    fun loadHolding() {

        viewModelScope.launch {
            holdingRepo.fetchHoldingList()
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = exception)
                }
                .collect { result ->
                    when (result) {

                        is ApiResponseState.Success -> {
                            _uiState.value = _uiState.value.copy(
                                holdingResults = result.data
                            )
                        }

                        is ApiResponseState.Error -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = result.exception
                            )
                        }

                        is ApiResponseState.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

    fun searchHolding(holdingName: String) {

    }

}
