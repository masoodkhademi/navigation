package com.mineobank.app.network

import com.mineobank.app.data.model.user.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface BaseDataApiService {

    @GET("user/profile")
    suspend fun getProfile(): Response<UserProfile>

    @PUT("user/profile")
    suspend fun updateProfile(@Body profile: UserProfile): Response<UserProfile>
}
