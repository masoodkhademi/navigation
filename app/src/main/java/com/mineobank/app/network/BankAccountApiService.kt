package com.mineobank.app.network

import com.mineobank.app.data.model.bankaccount.BankAccountInfo
import com.mineobank.app.data.model.bankaccount.CardInfo
import retrofit2.Response
import retrofit2.http.GET

interface BankAccountApiService {

    @GET("bank-account/info")
    suspend fun getAccountInfo(): Response<BankAccountInfo>

    @GET("bank-account/cards")
    suspend fun getCards(): Response<List<CardInfo>>
}
