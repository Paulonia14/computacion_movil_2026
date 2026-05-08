package com.compa.navigator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "NavigationExampleKMP_Ej4",
    ) {
        App()
    }
}