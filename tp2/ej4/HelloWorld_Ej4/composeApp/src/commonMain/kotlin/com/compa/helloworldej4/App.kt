package com.compa.helloworldej4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import helloworld_ej4.composeapp.generated.resources.Res
import helloworld_ej4.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var name:String by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = name,
                onValueChange = {name = it}
            )
            Spacer(modifier = Modifier.height(38.dp))
            if (name != "Paulonia") {
                AnimatedVisibility (name.isNotEmpty()){
                    Text(text = "Hola $name", fontSize = 28.sp)
            } } else {
                Text(text = "SUSCRIBITE a mi canal www.youtube.com/@paulonia !", fontSize = 40.sp, lineHeight= 45.sp, textAlign = TextAlign.Center )
            }
        }

    }
}
