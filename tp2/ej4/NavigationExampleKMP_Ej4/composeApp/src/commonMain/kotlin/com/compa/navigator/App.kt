package com.compa.navigator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import com.compa.navigator.bottombar.BottomBarScreen
import com.compa.navigator.settings.ProfileScreen
import org.jetbrains.compose.resources.painterResource

import navigationexamplekmp_ej4.composeapp.generated.resources.Res
import navigationexamplekmp_ej4.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun App() {
    MaterialTheme {
        Navigator(MainScreen()){navigator ->
            // 3 distintas transiciones básicas

            //SlideTransition(navigator)
            FadeTransition(navigator)
            //ScaleTransition(navigator)
        }
    }
}

class MainScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                navigator.push(SecondScreen()) //Manda a la segunda pantalla
            }) {
                Text("Navegación Básica")
            }
            Spacer(Modifier.height(18.dp))
            Button(onClick = {
                navigator.push(BottomBarScreen()) //Manda al BottomBar
            }) {
                Text("BottomBar")
            }
            Spacer(Modifier.height(18.dp))
            Button(onClick = {
                navigator.push(ProfileScreen()) //Manda al BottomBar
            }) {
                Text("Navegación con Persistencia")
            }
        }
    }
}
class SecondScreen:Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Segunda Pantalla", fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navigator.pop()
            }) {
                Text("Volver")
            }
        }
    }
}