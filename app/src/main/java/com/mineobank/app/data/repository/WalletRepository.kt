package com.mineobank.app.data.repository

import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.data.model.wallet.TransactionListResponse
import com.mineobank.app.data.model.wallet.WalletBalance
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.network.ApiConfig
import com.mineobank.app.network.NetworkClient
import com.mineobank.app.network.WalletApiService

class WalletRepository(tokenManager: TokenManager) {

    private val walletApi = NetworkClient.create(
        ApiConfig.WALLET_BASE_URL, WalletApiService::class.java, tokenManager
    )

    suspend fun getBalance(): ApiResult<WalletBalance> = safeCall {
        val response = walletApi.getBalance()
        if (response.isSuccessful) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(response.message(), response.code())
        }
    }

    suspend fun getTransactions(page: Int = 1, limit: Int = 20): ApiResult<List<Transaction>> =
        safeCall {
            val response = walletApi.getTransactions(page, limit)
            if (response.isSuccessful) {
                val items = response.body()?.items?.map { it.toTransaction() } ?: emptyList()
                ApiResult.Success(items)
            } else {
                ApiResult.Error(response.message(), response.code())
            }
        }
}

private inline fun <T> safeCall(block: () -> ApiResult<T>): ApiResult<T> = try {
    block()
} catch (e: Exception) {
    ApiResult.Error(e.localizedMessage ?: "Unexpected error")
}
