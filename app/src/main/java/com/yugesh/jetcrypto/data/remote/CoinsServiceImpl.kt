package com.yugesh.jetcrypto.data.remote

import com.yugesh.jetcrypto.data.remote.ApiEndPoints.LIST_COINS
import com.yugesh.jetcrypto.domain.model.CoinDetailsModel
import com.yugesh.jetcrypto.domain.model.CoinModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CoinsServiceImpl(
    private val client: HttpClient
) : CoinsService {

    override suspend fun getCoinList(): List<CoinModel> {
        return try {
            client.get(LIST_COINS).body()
        } catch (e: Exception) {
            println(e.message)
            emptyList()
        }
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailsModel {
        return try {
            client.get("$LIST_COINS/$coinId").body()
        } catch (e: Exception) {
            println(e.message)
            CoinDetailsModel()
        }
    }
}
