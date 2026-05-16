package com.mineobank.app.data.repository

import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.data.model.bankaccount.BankAccountInfo
import com.mineobank.app.data.model.bankaccount.CardInfo
import com.mineobank.app.data.model.user.UserProfile
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.network.ApiConfig
import com.mineobank.app.network.BankAccountApiService
import com.mineobank.app.network.BaseDataApiService
import com.mineobank.app.network.NetworkClient

class UserRepository(tokenManager: TokenManager) {

    private val baseDataApi = NetworkClient.create(
        ApiConfig.BASEDATA_BASE_URL, BaseDataApiService::class.java, tokenManager
    )
    private val bankAccountApi = NetworkClient.create(
        ApiConfig.BANK_ACCOUNT_BASE_URL, BankAccountApiService::class.java, tokenManager
    )

    suspend fun getProfile(): ApiResult<UserProfile> = safeCall {
        val response = baseDataApi.getProfile()
        if (response.isSuccessful) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(response.message(), response.code())
        }
    }

    suspend fun getBankAccountInfo(): ApiResult<BankAccountInfo> = safeCall {
        val response = bankAccountApi.getAccountInfo()
        if (response.isSuccessful) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(response.message(), response.code())
        }
    }

    suspend fun getCards(): ApiResult<List<CardInfo>> = safeCall {
        val response = bankAccountApi.getCards()
        if (response.isSuccessful) {
            ApiResult.Success(response.body() ?: emptyList())
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
