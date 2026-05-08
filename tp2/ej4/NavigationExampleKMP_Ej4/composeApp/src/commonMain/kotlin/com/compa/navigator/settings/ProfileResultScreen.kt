package com.compa.navigator.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compa.navigator.settings.ProfileScreen.Companion.KEY_VIP
import com.compa.navigator.settings.ProfileScreen.Companion.KEY_NAME
import com.russhwolf.settings.Settings

class ProfileResultScreen : Screen{
    private val settings: Settings = Settings()


    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        val isVip = settings.getBoolean(KEY_VIP, false)
        val backgroundColor = if(isVip) {
            Color.Green
        } else {
            Color.Red
        }
        Column (
            modifier = Modifier.fillMaxSize().background(backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val name = settings.getString(KEY_NAME, "")
            Text("HOLA $name", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {
                settings.remove(KEY_NAME)
                settings.remove(KEY_VIP)
                settings.clear()
                navigator.pop()
            }) {
                Text("Volver y borrar datos")
            }
        }
    }
}