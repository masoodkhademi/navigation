package com.mineobank.app.data.model.wallet

import com.google.gson.annotations.SerializedName
import com.mineobank.app.data.model.Transaction

data class WalletTransaction(
    @SerializedName("id")          val id: String?,
    @SerializedName("title")       val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("type")        val type: String?,        // "credit" | "debit" | "transfer"
    @SerializedName("category")    val category: String?,
    @SerializedName("amount")      val amount: Double?,
    @SerializedName("currency")    val currency: String?,
    @SerializedName("status")      val status: String?,
    @SerializedName("created_at")  val createdAt: String?,
    @SerializedName("date")        val date: String?
) {
    fun toTransaction(): Transaction {
        val isCredit = type?.lowercase() in listOf("credit", "income", "receive")
        val effectiveAmount = if (isCredit) (amount ?: 0.0) else -(amount ?: 0.0)
        val emoji = when (category?.lowercase()) {
            "entertainment" -> "🎬"
            "shopping"      -> "🛒"
            "food", "food & drink" -> "🍽️"
            "utilities"     -> "⚡"
            "health"        -> "💪"
            "income", "salary" -> "💼"
            "transfer"      -> "💸"
            "freelance"     -> "💻"
            else            -> if (isCredit) "💰" else "💳"
        }
        return Transaction(
            title    = title ?: description ?: "Transaction",
            category = category ?: type ?: "Other",
            amount   = effectiveAmount,
            date     = date ?: createdAt ?: "",
            emoji    = emoji
        )
    }
}

data class TransactionListResponse(
    @SerializedName("data")         val data: List<WalletTransaction>?,
    @SerializedName("transactions") val transactions: List<WalletTransaction>?,
    @SerializedName("total")        val total: Int?,
    @SerializedName("page")         val page: Int?,
    @SerializedName("limit")        val limit: Int?
) {
    val items: List<WalletTransaction> get() = data ?: transactions ?: emptyList()
}

data class TransferRequest(
    @SerializedName("to")       val to: String,
    @SerializedName("amount")   val amount: Double,
    @SerializedName("currency") val currency: String = "USD",
    @SerializedName("note")     val note: String? = null
)

data class TransferResponse(
    @SerializedName("success")        val success: Boolean?,
    @SerializedName("transaction_id") val transactionId: String?,
    @SerializedName("message")        val message: String?
)
