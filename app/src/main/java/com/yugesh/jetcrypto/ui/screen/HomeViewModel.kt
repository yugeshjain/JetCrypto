package com.yugesh.jetcrypto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.repo.CoinsRepo
import com.yugesh.jetcrypto.ui.util.roundOffDecimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

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

    suspend fun getCoinDetail(coinId: String): CoinDetails? {
        return try {
            val asyncCall = viewModelScope.async(Dispatchers.IO) {
                coinsRepo.getCoinPriceDetails(coinId = coinId)
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

data class CoinDetails(
    val id: String = "",
    val name: String = "",
    val symbol: String = "",
    val price: String = "",
    val rank: String = "",
    val detailsFetchedSuccessfully: Boolean = true
)
