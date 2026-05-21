package com.example.kotlintp2e2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlintp2e2.ui.theme.KotlinTP2E2Theme
import com.example.kotlintp2e2.ui.theme.Typography

@Composable
fun MainView(modifier: Modifier, devices: List<Device>) {
    Column(modifier) {
        Text(
            text = "Tienda",
            //modifier = modifier.background(Color.Red).padding(20.dp).fillMaxWidth(),
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            style = Typography.displayLarge,
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        LazyColumn {
            items(devices.size) { index ->
                DeviceItemView(devices[index])
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    KotlinTP2E2Theme {
        MainView(
            Modifier.padding(top = 24.dp),
            listOf(
                Device(1,"Samsung Galaxy A22 5G", Specs("Black", "128GB", "$150")),
                Device(2, "Samsung Galaxy A14", Specs("Black", "128GB", "$135")),
                Device(3, "Motorola G28", null)
            )
        )
    }
}