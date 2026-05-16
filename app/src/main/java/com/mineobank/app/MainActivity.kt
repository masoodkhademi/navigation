package com.mineobank.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.mineobank.app.data.local.TokenManager
import com.mineobank.app.ui.navigation.MineoNavHost
import com.mineobank.app.ui.navigation.Screen
import com.mineobank.app.ui.theme.MineoNavy
import com.mineobank.app.ui.theme.MineoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MineoTheme {
                val navController = rememberNavController()
                val tokenManager = remember { TokenManager(this) }
                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    delay(1500)
                    startDestination = if (tokenManager.getAccessToken() != null) {
                        Screen.Home.route
                    } else {
                        Screen.Login.route
                    }
                }

                if (startDestination != null) {
                    MineoNavHost(
                        navController = navController,
                        startDestination = startDestination!!,
                        tokenManager = tokenManager
                    )
                } else {
                    SplashContent()
                }
            }
        }
    }
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MineoNavy),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "✏️", fontSize = 52.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "ماینو بانک",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "بانکداری هوشمند",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 16.sp
            )
        }
    }
}
