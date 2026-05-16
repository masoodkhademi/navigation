package com.mineobank.app.ui.transactions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.databinding.ItemTransactionBinding
import kotlin.math.abs

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.tvEmoji.text = transaction.emoji
            binding.tvTitle.text = transaction.title
            binding.tvCategory.text = transaction.category
            binding.tvDate.text = transaction.date

            if (transaction.amount >= 0) {
                binding.tvAmount.text = "+$${String.format("%.2f", transaction.amount)}"
                binding.tvAmount.setTextColor(Color.parseColor("#22C55E"))
            } else {
                binding.tvAmount.text = "-$${String.format("%.2f", abs(transaction.amount))}"
                binding.tvAmount.setTextColor(Color.parseColor("#1A1D2E"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount() = transactions.size
}
