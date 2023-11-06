package com.yugesh.jetcrypto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val coinsRepo: CoinsRepo
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState.asStateFlow()

    fun getCoinList() {
        loadingStarted()
        viewModelScope.launch(Dispatchers.IO) {
            _homeScreenUiState.update { uiState ->
                uiState.copy(
                    coinsList = coinsRepo.getCoinList(),
                    isLoading = false
                )
            }
        }
    }

    fun getCoinDetail(coinId: String): CoinDetailsModel {
        var result = CoinDetailsModel()
        viewModelScope.launch(Dispatchers.IO) {
            result = coinsRepo.getCoinDetails(coinId = coinId)
        }
        return result
    }

    private fun loadingStarted(){
        _homeScreenUiState.update { uiState ->
            uiState.copy(
                isLoading = true
            )
        }
    }
}

data class HomeScreenUiState(
    val coinsList: List<CoinModel> = emptyList(),
    val isLoading: Boolean = false
)
