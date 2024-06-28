package com.example.shaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                        modifier = Modifier.shakable(shakingState)
                    ) {
                        Text(text = "Shakiiing !!", fontSize = 22.sp)
                    }
                }
            }
        }
    }
}