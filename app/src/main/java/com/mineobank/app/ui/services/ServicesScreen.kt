package com.mineobank.app.ui.services

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mineobank.app.ui.theme.MineoNavy

private val services = listOf(
    "📱" to "خرید شارژ",
    "💸" to "حواله",
    "📦" to "خرید بسته",
    "🏦" to "وام",
    "🛡️" to "بیمه",
    "📄" to "قبض",
    "💳" to "کارت",
    "🥇" to "طلا",
    "⋯" to "بیشتر"
)

@Composable
fun ServicesScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF4F6FA))
            .verticalScroll(rememberScrollState())
    ) {
        // Navy header
        Box(
            modifier = Modifier.fillMaxWidth().background(MineoNavy)
                .padding(horizontal = 20.dp, vertical = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("خدمات ماینو", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                services.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        row.forEach { (emoji, label) ->
                            Column(
                                modifier = Modifier.weight(1f).padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier.size(60.dp).clip(CircleShape)
                                        .background(Color(0xFFF4F6FA)),
                                    contentAlignment = Alignment.Center
                                ) { Text(emoji, fontSize = 26.sp) }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    label,
                                    color = MineoNavy,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        repeat(3 - row.size) { Spacer(modifier = Modifier.weight(1f)) }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
