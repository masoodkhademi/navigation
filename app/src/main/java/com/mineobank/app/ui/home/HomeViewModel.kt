package com.mineobank.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.data.model.wallet.WalletBalance
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.data.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val tokenManager = TokenManager(application)
    private val walletRepository = WalletRepository(tokenManager)

    private val _balance = MutableStateFlow<ApiResult<WalletBalance>>(ApiResult.Loading)
    val balance: StateFlow<ApiResult<WalletBalance>> = _balance

    private val _transactions = MutableStateFlow<ApiResult<List<Transaction>>>(ApiResult.Loading)
    val transactions: StateFlow<ApiResult<List<Transaction>>> = _transactions

    val userName: String get() = tokenManager.getUserName() ?: "علی رضایی"

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _balance.value = ApiResult.Loading
            _balance.value = walletRepository.getBalance()
        }
        viewModelScope.launch {
            _transactions.value = ApiResult.Loading
            _transactions.value = walletRepository.getTransactions(limit = 5)
        }
    }

    companion object {
        fun sampleTransactions() = listOf(
            Transaction("انتقال وجه", "حواله", -500_000.0, "امروز، ۱۰:۳۰", "↑"),
            Transaction("دریافت وجه", "واریز", 2_000_000.0, "دیروز، ۱۴:۰۰", "↓"),
            Transaction("خرید شارژ", "خدمات", -50_000.0, "دیروز، ۱۱:۱۵", "📱"),
            Transaction("پرداخت قبض", "قبوض", -120_000.0, "۲ روز پیش", "📄"),
            Transaction("واریز حقوق", "درآمد", 15_000_000.0, "۳ روز پیش", "💼")
        )
    }
}
