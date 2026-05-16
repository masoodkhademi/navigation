package com.mineobank.app.ui.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mineobank.app.ui.theme.MineoDivider
import com.mineobank.app.ui.theme.MineoError
import com.mineobank.app.ui.theme.MineoNavy
import com.mineobank.app.ui.theme.MineoSuccess
import com.mineobank.app.ui.theme.MineoTextLight
import com.mineobank.app.ui.theme.MineoTextMedium

@Composable
fun WalletScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF4F6FA))
            .verticalScroll(rememberScrollState())
    ) {
        // Navy header
        Column(
            modifier = Modifier.fillMaxWidth().background(MineoNavy)
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
                Text("کیف پول ماینو", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier.size(40.dp).clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) { Text("👛", fontSize = 18.sp) }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("👛", fontSize = 22.sp)
                        Text("کارت پول ماینو", color = MineoNavy, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("۱۲۳،۵۴۳،۰۰۰", color = MineoNavy, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text("موجودی ارزیابی (تومان)", color = MineoTextLight, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("↑" to "انتقال کیف", "↓" to "دریافت", "+" to "شارژ کیف").forEach { (icon, label) ->
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
                            Text(label, color = MineoNavy, fontSize = 11.sp, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // QR section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("کد QR حساب شما", color = MineoNavy, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.size(180.dp)
                        .border(2.dp, MineoDivider, RoundedCornerShape(12.dp))
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("▪▪▪\n▪ ▪\n▪▪▪", color = MineoNavy, fontSize = 40.sp, textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "IR۱۲ ۰۳۴۰ ۶۵۸۰ ۰۰۰۰ ۱۰۰۶ ۰۱۳۷",
                    color = MineoTextMedium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Transactions
        Card(
            modifier = Modifier.fillMaxWidth(),
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
                Spacer(modifier = Modifier.height(8.dp))
                listOf(
                    Triple("↑", "انتقال وجه", "-۵۰،۰۰۰"),
                    Triple("↓", "دریافت وجه", "+۲۰۰،۰۰۰")
                ).forEach { (icon, title, amount) ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val isIncome = amount.startsWith("+")
                        Text(
                            "$amount تومان",
                            color = if (isIncome) MineoSuccess else MineoError,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                            Text(title, color = MineoNavy, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            Text("امروز", color = MineoTextLight, fontSize = 12.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier.size(44.dp).clip(CircleShape)
                                .background(Color(0xFFF4F6FA)),
                            contentAlignment = Alignment.Center
                        ) { Text(icon, fontSize = 18.sp) }
                    }
                    HorizontalDivider(color = MineoDivider)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
