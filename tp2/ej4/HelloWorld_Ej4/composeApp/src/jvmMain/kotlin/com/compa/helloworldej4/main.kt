package com.compa.helloworldej4

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "HelloWorld_Ej4",
    ) {
        App()
    }
}