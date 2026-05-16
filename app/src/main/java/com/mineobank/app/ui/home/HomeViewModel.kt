package com.mineobank.app.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.data.model.wallet.WalletBalance
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.data.repository.WalletRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {

    private val tokenManager = TokenManager(context)
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
        loadBalance()
        loadTransactions()
    }

    private fun loadBalance() {
        _balance.value = ApiResult.Loading
        viewModelScope.launch {
            _balance.value = walletRepository.getBalance()
        }
    }

    private fun loadTransactions() {
        _transactions.value = ApiResult.Loading
        viewModelScope.launch {
            _transactions.value = walletRepository.getTransactions(limit = 5)
        }
    }
}
