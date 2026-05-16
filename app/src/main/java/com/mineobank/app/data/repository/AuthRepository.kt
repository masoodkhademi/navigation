package com.mineobank.app.data.repository

import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.data.model.auth.AuthResponse
import com.mineobank.app.data.model.auth.LoginRequest
import com.mineobank.app.data.model.auth.RegisterRequest
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.network.ApiConfig
import com.mineobank.app.network.AuthApiService
import com.mineobank.app.network.NetworkClient

class AuthRepository(private val tokenManager: TokenManager) {

    private val authApi = NetworkClient.createPublic(ApiConfig.AUTH_BASE_URL, AuthApiService::class.java)

    suspend fun login(phone: String, password: String): ApiResult<AuthResponse> = safeCall {
        val request = if (phone.contains("@")) {
            LoginRequest(email = phone, password = password)
        } else {
            LoginRequest(phone = phone, password = password)
        }
        val response = authApi.login(request)
        if (response.isSuccessful) {
            val body = response.body()
            val token = body?.effectiveToken
            if (token != null) {
                tokenManager.saveAccessToken(token)
                body.refreshToken?.let { tokenManager.saveRefreshToken(it) }
                body.user?.let { user ->
                    user.effectiveId?.let { tokenManager.saveUserId(it) }
                    tokenManager.saveUserName(user.fullName)
                    user.email?.let { tokenManager.saveUserEmail(it) }
                }
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Invalid response from server")
            }
        } else {
            ApiResult.Error(response.message(), response.code())
        }
    }

    suspend fun register(
        firstName: String, lastName: String,
        email: String, phone: String, password: String
    ): ApiResult<AuthResponse> = safeCall {
        val response = authApi.register(
            RegisterRequest(firstName, lastName, email, phone, password)
        )
        if (response.isSuccessful) {
            val body = response.body()
            val token = body?.effectiveToken
            if (token != null) {
                tokenManager.saveAccessToken(token)
                body.refreshToken?.let { tokenManager.saveRefreshToken(it) }
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Registration failed")
            }
        } else {
            ApiResult.Error(response.message(), response.code())
        }
    }

    fun logout() = tokenManager.clearAll()
}

private inline fun <T> safeCall(block: () -> ApiResult<T>): ApiResult<T> = try {
    block()
} catch (e: Exception) {
    ApiResult.Error(e.localizedMessage ?: "Unexpected error")
}
