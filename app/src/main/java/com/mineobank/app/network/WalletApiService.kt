package com.mineobank.app.network

import com.mineobank.app.data.model.wallet.TransactionListResponse
import com.mineobank.app.data.model.wallet.TransferRequest
import com.mineobank.app.data.model.wallet.TransferResponse
import com.mineobank.app.data.model.wallet.WalletBalance
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WalletApiService {

    @GET("wallet/balance")
    suspend fun getBalance(): Response<WalletBalance>

    @GET("wallet/transactions")
    suspend fun getTransactions(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<TransactionListResponse>

    @POST("wallet/transfer")
    suspend fun transfer(@Body request: TransferRequest): Response<TransferResponse>

    @POST("wallet/topup")
    suspend fun topUp(@Body request: TransferRequest): Response<TransferResponse>
}
