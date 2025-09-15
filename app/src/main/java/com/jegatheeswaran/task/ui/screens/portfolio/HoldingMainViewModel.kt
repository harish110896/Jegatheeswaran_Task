package com.jegatheeswaran.task.ui.screens.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jegatheeswaran.task.data.local.LocalHoldingRepository
import com.jegatheeswaran.task.data.local.LocalHoldingRepositoryImpl
import com.jegatheeswaran.task.data.model.toDtoList
import com.jegatheeswaran.task.data.model.toEntityList
import com.jegatheeswaran.task.data.remote.HoldingRepositoryImpl
import com.jegatheeswaran.task.utils.network.ApiResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingMainViewModel @Inject constructor(
    private val holdingRepo: HoldingRepositoryImpl,
    private val localHoldingRepository: LocalHoldingRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HoldingUiState())
    val uiState: StateFlow<HoldingUiState> get() = _uiState.asStateFlow()


    fun loadHolding(isConnected: Boolean) {
        if(isConnected){
            loadFromApi()
        }else {
            loadFromDb()
        }
    }

    private fun loadFromDb() {

        viewModelScope.launch {
            val list = localHoldingRepository.loadHolding()
            _uiState.value = _uiState.value.copy(
                holdingResults = list.toDtoList()
            )
        }
    }

    fun loadFromApi(){
        viewModelScope.launch {
            holdingRepo.fetchHoldingList()
                .onStart {
                    _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, error = exception)
                }
                .onCompletion {
                    _uiState.value = _uiState.value.copy(isLoading = false, error = null)
                }
                .collect { result ->
                    when (result) {
                        is ApiResponseState.Success -> {
                            _uiState.value = _uiState.value.copy(
                                holdingResults = result.data
                            )
                            val list = _uiState.value.holdingResults.toEntityList()
                            localHoldingRepository.insertAll(list)
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

}
