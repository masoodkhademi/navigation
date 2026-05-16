package com.mineobank.app.data.model.wallet

import com.google.gson.annotations.SerializedName

data class WalletBalance(
    @SerializedName("balance")          val balance: Double?,
    @SerializedName("available_balance") val availableBalance: Double?,
    @SerializedName("currency")         val currency: String?,
    @SerializedName("currency_symbol")  val currencySymbol: String?,
    @SerializedName("wallet_id")        val walletId: String?
) {
    val effectiveBalance: Double get() = availableBalance ?: balance ?: 0.0
    val symbol: String get() = currencySymbol ?: "$"
    fun formatted(): String = "$symbol${String.format("%,.2f", effectiveBalance)}"
}
