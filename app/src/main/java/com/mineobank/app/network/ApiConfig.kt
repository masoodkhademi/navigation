package com.mineobank.app.network

object ApiConfig {
    // Use 10.0.2.2 for Android emulator (maps to host machine localhost)
    // Replace with your server IP for a physical device
    private const val BASE_HOST = "http://10.0.2.2"

    const val AUTH_BASE_URL         = "$BASE_HOST:5003/"
    const val BASEDATA_BASE_URL     = "$BASE_HOST:5001/"
    const val WALLET_BASE_URL       = "$BASE_HOST:5005/"
    const val SERVICEHUB_BASE_URL   = "$BASE_HOST:5006/"
    const val SUPPORT_BASE_URL      = "$BASE_HOST:5007/"
    const val LOGS_BASE_URL         = "$BASE_HOST:5009/"
    const val MEDIA_BASE_URL        = "$BASE_HOST:5010/"
    const val MINI_APP_BASE_URL     = "$BASE_HOST:5011/"
    const val BANK_ACCOUNT_BASE_URL = "$BASE_HOST:5012/"
}
