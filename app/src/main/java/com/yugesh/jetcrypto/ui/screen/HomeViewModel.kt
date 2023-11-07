package com.yugesh.jetcrypto.ui.screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.usecase.GetCoinPriceDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinsListUseCase
import com.yugesh.jetcrypto.ui.util.roundOffDecimal
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val getCoinPriceDetailsUseCase: GetCoinPriceDetailsUseCase
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState: StateFlow<HomeScreenUiState> = _homeScreenUiState.asStateFlow()

    init {
        getCoinList()
    }

    fun getCoinList() {
        loadingStarted()
        viewModelScope.launch {
            _homeScreenUiState.update { uiState ->
                uiState.copy(
                    coinsList = getCoinsListUseCase(),
                    isLoading = false
                )
            }
        }
    }

    suspend fun getCoinDetail(coinId: String): CoinDetails? {
        return try {
            val asyncCall = viewModelScope.async {
                getCoinPriceDetailsUseCase(coinId = coinId)
            }
            val details = asyncCall.await()
            CoinDetails(
                id = details.id.orEmpty(),
                name = details.name.orEmpty(),
                symbol = details.symbol.orEmpty(),
                price = details.quotes?.usd?.price?.roundOffDecimal().toString(),
                rank = details.rank.toString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
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

@Immutable
data class CoinDetails(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val price: String = "",
    val rank: String = "",
    val detailsFetchedSuccessfully: Boolean = true
)
