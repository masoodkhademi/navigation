package com.mineobank.app.data.model.user

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("id")          val id: String?,
    @SerializedName("first_name")  val firstName: String?,
    @SerializedName("last_name")   val lastName: String?,
    @SerializedName("name")        val name: String?,
    @SerializedName("email")       val email: String?,
    @SerializedName("phone")       val phone: String?,
    @SerializedName("avatar")      val avatar: String?,
    @SerializedName("avatar_url")  val avatarUrl: String?,
    @SerializedName("created_at")  val createdAt: String?,
    @SerializedName("is_premium")  val isPremium: Boolean?
) {
    val fullName: String
        get() = when {
            name != null -> name
            firstName != null || lastName != null ->
                listOfNotNull(firstName, lastName).joinToString(" ")
            else -> "User"
        }

    val initials: String
        get() = fullName.split(" ")
            .mapNotNull { it.firstOrNull()?.uppercase() }
            .take(2)
            .joinToString("")
            .ifEmpty { "U" }
}
