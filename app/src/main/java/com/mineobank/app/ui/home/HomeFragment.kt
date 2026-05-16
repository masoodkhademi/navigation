package com.mineobank.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.databinding.FragmentHomeBinding
import com.mineobank.app.ui.transactions.TransactionAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvRecentTransactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TransactionAdapter(emptyList())
        }
    }

    private fun observeViewModel() {
        viewModel.balance.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Success -> binding.tvBalance.text = result.data.formatted()
                is ApiResult.Error   -> { /* keep placeholder balance */ }
                is ApiResult.Loading -> { /* optionally show shimmer */ }
            }
        }

        viewModel.transactions.observe(viewLifecycleOwner) { result ->
            val list = when (result) {
                is ApiResult.Success -> result.data
                else                 -> sampleTransactions().take(5)
            }
            binding.rvRecentTransactions.adapter = TransactionAdapter(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun sampleTransactions(): List<Transaction> = listOf(
            Transaction("انتقال وجه",        "خدمات مالی",   -500000.0,  "امروز، ۱۰:۳۰",        "↑"),
            Transaction("دریافت حقوق",       "درآمد",         8500000.0,  "دیروز، ۹:۰۰",         "↓"),
            Transaction("خرید فروشگاه",       "خرید",         -350000.0,  "۱۴ اسفند، ۱۴:۳۰",    "🛒"),
            Transaction("پرداخت قبض برق",    "قبوض",          -125000.0,  "۱۳ اسفند، ۱۱:۰۰",    "⚡"),
            Transaction("خرید شارژ",          "خدمات",         -50000.0,   "۱۲ اسفند، ۸:۱۵",     "📱"),
            Transaction("دریافت وجه",         "درآمد",         2000000.0,  "۱۱ اسفند، ۱۵:۰۰",    "↓"),
            Transaction("خرید اینترنتی",      "خرید",         -220000.0,  "۱۰ اسفند، ۱۳:۲۰",    "📦"),
            Transaction("پرداخت بیمه",        "بیمه",          -180000.0,  "۹ اسفند، ۱۹:۳۰",     "🛡️"),
            Transaction("اشتراک خدمات",       "خدمات",         -95000.0,   "۸ اسفند، ۶:۰۰",      "📺"),
            Transaction("انتقال دریافتی",     "درآمد",         1500000.0,  "۷ اسفند، ۱۱:۰۰",     "↓")
        )
    }
}
