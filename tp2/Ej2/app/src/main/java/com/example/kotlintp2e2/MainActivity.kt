package com.example.kotlintp2e2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlintp2e2.ui.theme.KotlinTP2E2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTP2E2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(Modifier.padding(innerPadding))
                }
            }
        }

    }
}

@Composable
fun MainView(modifier: Modifier) {
    Text(text = "Comprar",
        modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinTP2E2Theme {
        MainView(Modifier)
    }
}