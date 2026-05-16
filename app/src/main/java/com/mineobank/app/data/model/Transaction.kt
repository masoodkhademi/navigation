package com.mineobank.app.data.model

data class Transaction(
    val title: String,
    val category: String,
    val amount: Double,
    val date: String,
    val emoji: String
)
