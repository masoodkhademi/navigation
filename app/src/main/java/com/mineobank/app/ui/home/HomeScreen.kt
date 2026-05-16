package com.mineobank.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mineobank.app.data.model.Transaction
import com.mineobank.app.data.remote.ApiResult
import com.mineobank.app.ui.theme.MineoDivider
import com.mineobank.app.ui.theme.MineoError
import com.mineobank.app.ui.theme.MineoNavy
import com.mineobank.app.ui.theme.MineoSuccess
import com.mineobank.app.ui.theme.MineoTextLight
import com.mineobank.app.ui.theme.MineoTextMedium

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val balanceState by viewModel.balance.collectAsStateWithLifecycle()
    val txState by viewModel.transactions.collectAsStateWithLifecycle()

    val balanceText = when (balanceState) {
        is ApiResult.Success -> (balanceState as ApiResult.Success).data.formatted()
        else -> "۱۲۳،۵۴۳،۰۰۰ تومان"
    }
    val txList = when (txState) {
        is ApiResult.Success -> (txState as ApiResult.Success).data
        else -> HomeViewModel.sampleTransactions()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color(0xFFF4F6FA))) {
        item {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MineoNavy)
                    .padding(horizontal = 20.dp, vertical = 48.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) { Text("👤", fontSize = 18.sp) }
                    Text("ماینو بانک", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) { Text("م", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold) }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Balance card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("موجودی", color = MineoTextLight, fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = balanceText,
                                color = MineoNavy,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 14.dp), color = MineoDivider)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(38.dp).clip(CircleShape)
                                    .background(MineoNavy),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = viewModel.userName.take(2),
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(viewModel.userName, color = MineoNavy, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Action buttons
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("↑" to "انتقال", "↓" to "دریافت", "☰" to "تاریخچه").forEach { (icon, label) ->
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(icon, color = MineoNavy, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(label, color = MineoNavy, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp),
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = {}) { Text("مشاهده همه", color = MineoNavy, fontSize = 13.sp) }
                        Text("تراکنش‌های اخیر", color = MineoNavy, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        items(txList) { tx ->
            TransactionRow(tx)
            HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = MineoDivider)
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun TransactionRow(tx: Transaction) {
    val isIncome = tx.amount > 0
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.White)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isIncome) "+ ${formatAmount(tx.amount)} تومان" else "- ${formatAmount(-tx.amount)} تومان",
            color = if (isIncome) MineoSuccess else MineoError,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
            Text(tx.description, color = MineoNavy, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(tx.date, color = MineoTextMedium, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier.size(44.dp).clip(CircleShape).background(Color(0xFFF4F6FA)),
            contentAlignment = Alignment.Center
        ) { Text(tx.icon, fontSize = 18.sp) }
    }
}

private fun formatAmount(amount: Double): String {
    return String.format("%,.0f", amount)
}
