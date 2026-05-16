package com.mineobank.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.ui.theme.MineoDivider
import com.mineobank.app.ui.theme.MineoError
import com.mineobank.app.ui.theme.MineoNavy
import com.mineobank.app.ui.theme.MineoTextLight
import com.mineobank.app.ui.theme.MineoTextMedium

@Composable
fun ProfileScreen(tokenManager: TokenManager, onLogout: () -> Unit) {
    val userName = tokenManager.getUserName() ?: "علی رضایی"
    val userEmail = tokenManager.getUserEmail() ?: "ali.rezaei@email.com"
    val initials = userName.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF4F6FA))
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Column(
            modifier = Modifier.fillMaxWidth().background(MineoNavy)
                .padding(horizontal = 24.dp, vertical = 52.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(80.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(initials.ifEmpty { "ع" }, color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(userName, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(userEmail, color = Color.White.copy(alpha = 0.65f), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(14.dp))
            Box(
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF4C9AFF).copy(alpha = 0.25f))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) { Text("کاربر ویژه", color = Color(0xFF4C9AFF), fontSize = 12.sp, fontWeight = FontWeight.Medium) }
        }

        Spacer(modifier = Modifier.height(8.dp))

        ProfileSection(
            title = "حساب کاربری",
            items = listOf(
                Triple("👤", "احراز هویت", false),
                Triple("🔔", "ابزارهای کاربر", false),
                Triple("🔐", "امنیت اطلاعات", false)
            ),
            onItemClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        ProfileSection(
            title = "پشتیبانی",
            items = listOf(
                Triple("❓", "تیکت‌های پشتیبانی", false),
                Triple("📋", "قوانین و مقررات", false),
                Triple("🕐", "ساعت‌های بانکی", false)
            ),
            onItemClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable(onClick = onLogout)
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("خروج از حساب کاربری", color = MineoError, fontSize = 15.sp, fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f))
                Text("🚪", fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun ProfileSection(
    title: String,
    items: List<Triple<String, String, Boolean>>,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            Text(title, color = MineoTextLight, fontSize = 12.sp, modifier = Modifier.padding(bottom = 8.dp))
            items.forEachIndexed { index, (emoji, label, _) ->
                Row(
                    modifier = Modifier.fillMaxWidth().height(60.dp).clickable { onItemClick(label) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(38.dp).clip(CircleShape).background(Color(0xFFF4F6FA)),
                        contentAlignment = Alignment.Center
                    ) { Text(emoji, fontSize = 17.sp) }
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(label, color = MineoNavy, fontSize = 15.sp, modifier = Modifier.weight(1f))
                    Text("‹", color = MineoTextMedium, fontSize = 22.sp)
                }
                if (index < items.lastIndex) HorizontalDivider(color = MineoDivider)
            }
        }
    }
}
