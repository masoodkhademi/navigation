package com.mineobank.app.network

import com.mineobank.app.data.model.auth.AuthResponse
import com.mineobank.app.data.model.auth.LoginRequest
import com.mineobank.app.data.model.auth.RefreshTokenRequest
import com.mineobank.app.data.model.auth.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
}
