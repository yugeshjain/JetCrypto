package com.yugesh.jetcrypto

import com.yugesh.jetcrypto.domain.model.CoinModel
import com.yugesh.jetcrypto.domain.model.CoinPriceDetailsModel
import com.yugesh.jetcrypto.domain.model.Quotes
import com.yugesh.jetcrypto.domain.model.USD
import com.yugesh.jetcrypto.domain.repo.CoinsRepo
import com.yugesh.jetcrypto.domain.usecase.GetCoinPriceDetailsUseCase
import com.yugesh.jetcrypto.domain.usecase.GetCoinsListUseCase
import com.yugesh.jetcrypto.ui.screen.CoinDetails
import com.yugesh.jetcrypto.ui.screen.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = ViewModelTestRule()

    @MockK
    private lateinit var coinsRepo: CoinsRepo
    private lateinit var viewModelUnderTest: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = false)
        viewModelUnderTest = HomeViewModel(
            getCoinsListUseCase = GetCoinsListUseCase(coinsRepo = coinsRepo),
            getCoinPriceDetailsUseCase = GetCoinPriceDetailsUseCase(coinsRepo = coinsRepo)
        )
    }

    @Test
    fun `getCoinList should call the GetCoinsListUseCase and update the HomeScreenUiState with the result`() = runTest {
        // Arrange
        coEvery { coinsRepo.getCoinList() } returns fakeCoinsListResponse

        // Act
        viewModelUnderTest.getCoinList()

        // Assert
        val uiState = viewModelUnderTest.homeScreenUiState.value
        TestCase.assertEquals(uiState.coinsList, fakeCoinsListResponse)
        assert(!uiState.isLoading)
    }

    @Test
    fun `getCoinDetail should call the GetCoinPriceDetailsUseCase and return the CoinDetails if successful`() = runTest {
        // Arrange
        val expectedCoinId = "bitcoin"
//        coEvery { coinsRepo.getCoinList() } returns fakeCoinsListResponse
        coEvery { coinsRepo.getCoinPriceDetails(coinId = expectedCoinId) } returns fakeCoinPriceDetail

        // Act
        val result: CoinDetails? = viewModelUnderTest.getCoinDetail(expectedCoinId)

        // Assert
        TestCase.assertEquals(result, fakeCoinDetails)
    }

    @Test
    fun `getCoinDetail should return null if the GetCoinPriceDetailsUseCase throws an exception`() = runTest{
        // Arrange
        val expectedCoinId = "bitcoin"
        val expectedException = Exception("Failed to fetch coin details")
        coEvery { coinsRepo.getCoinPriceDetails(coinId = expectedCoinId) } throws expectedException

        // Act
        val result: CoinDetails? = viewModelUnderTest.getCoinDetail(expectedCoinId)

        // Assert
        assert(result == null)
    }
}

val fakeCoinsListResponse = listOf(
    CoinModel(
        id = "btc-bitcoin",
        isActive = true,
        isNew = false,
        type = "coin",
        name = "Bitcoin",
        rank = 1,
        symbol = "BTC"
    )
)

val fakeCoinPriceDetail = CoinPriceDetailsModel(
    id = "bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    rank = 1,
    quotes = Quotes(
        usd = USD(
            price = 1000.0
        )
    )
)

val fakeCoinDetails = CoinDetails(
    id = "bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    rank = "1",
    price = "1000.0"
)
