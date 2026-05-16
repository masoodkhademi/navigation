package com.mineobank.app.data.model.bankaccount

import com.google.gson.annotations.SerializedName

data class BankAccountInfo(
    @SerializedName("account_number") val accountNumber: String?,
    @SerializedName("iban")           val iban: String?,
    @SerializedName("swift")          val swift: String?,
    @SerializedName("bank_name")      val bankName: String?,
    @SerializedName("currency")       val currency: String?,
    @SerializedName("status")         val status: String?
)

data class CardInfo(
    @SerializedName("id")           val id: String?,
    @SerializedName("card_number")  val cardNumber: String?,
    @SerializedName("card_holder")  val cardHolder: String?,
    @SerializedName("expiry_month") val expiryMonth: String?,
    @SerializedName("expiry_year")  val expiryYear: String?,
    @SerializedName("card_type")    val cardType: String?,
    @SerializedName("is_active")    val isActive: Boolean?,
    @SerializedName("is_frozen")    val isFrozen: Boolean?
) {
    val maskedNumber: String
        get() = cardNumber?.let {
            val last4 = it.takeLast(4)
            "•••• •••• •••• $last4"
        } ?: "•••• •••• •••• ••••"

    val expiry: String
        get() = if (expiryMonth != null && expiryYear != null) "$expiryMonth/$expiryYear" else "--/--"
}
