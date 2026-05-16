package com.mineobank.app.data.model.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")  val accessToken: String?,
    @SerializedName("token")         val token: String?,        // alternate field name
    @SerializedName("refresh_token") val refreshToken: String?,
    @SerializedName("token_type")    val tokenType: String?,
    @SerializedName("expires_in")    val expiresIn: Long?,
    @SerializedName("user")          val user: AuthUser?,
    @SerializedName("message")       val message: String?,
    @SerializedName("success")       val success: Boolean?
) {
    val effectiveToken: String? get() = accessToken ?: token
}

data class AuthUser(
    @SerializedName("id")         val id: String?,
    @SerializedName("_id")        val mongoId: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name")  val lastName: String?,
    @SerializedName("name")       val name: String?,
    @SerializedName("email")      val email: String?,
    @SerializedName("phone")      val phone: String?
) {
    val effectiveId: String? get() = id ?: mongoId
    val fullName: String
        get() = when {
            name != null -> name
            firstName != null || lastName != null ->
                listOfNotNull(firstName, lastName).joinToString(" ")
            else -> "User"
        }
}
