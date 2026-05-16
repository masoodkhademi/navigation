package com.mineobank.app.data.model.support

import com.google.gson.annotations.SerializedName

data class SupportTicket(
    @SerializedName("id")          val id: String?,
    @SerializedName("subject")     val subject: String?,
    @SerializedName("status")      val status: String?,
    @SerializedName("created_at")  val createdAt: String?
)

data class SupportTicketRequest(
    @SerializedName("subject")     val subject: String,
    @SerializedName("description") val description: String,
    @SerializedName("category")    val category: String? = null
)
