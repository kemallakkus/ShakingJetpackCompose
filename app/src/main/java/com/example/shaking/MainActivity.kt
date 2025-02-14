package com.example.shaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shaking.ui.ShakingState
import com.example.shaking.ui.rememberShakingState
import com.example.shaking.ui.shakable
import com.example.shaking.ui.theme.ShakingTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShakingTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black, shape = RoundedCornerShape(12.dp))
                    , contentAlignment = Alignment.Center) {
                    val shakingState = rememberShakingState(
                        strength = ShakingState.Strength.Custom(100f),
                        direction = ShakingState.Directions.LEFT_THEN_RIGHT
                    )
                    val scope = rememberCoroutineScope()

                    TextButton(
                        onClick = {
                            scope.launch {
                                shakingState.shake(animationDuration = 30)
                            }
                        },
                        modifier = Modifier
                            .shakable(shakingState)
                            .background(Color.White, shape = RoundedCornerShape(12.dp))
                            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.secondary), shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Shakiiing !!",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
