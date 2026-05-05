package com.compa.navigator.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

class ProfileScreen : Screen {

    private val settings: Settings = Settings()

    companion object {
        const val KEY_NAME = "NAME"
        const val KEY_VIP = "VIP"
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var name by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }
        Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.weight(1f))
            TextField(value = name, onValueChange = {name = it})
            Row (verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isVip , onCheckedChange = {isVip = it})
                Text("Sos VIP????")
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                //settings.putString("NAME", name) y settings["KEY_NAME"] = name Son lo mismo

                //Guardamos los datos
                settings[KEY_NAME] = name
                settings[KEY_VIP] = isVip
                navigator.push(ProfileResultScreen())

            }, enabled= name.isNotEmpty()){ //Si el campo de texto está vacío, no se habilita el boton
                Text("Guardar Perfil")
            }
            Button(onClick = { navigator.popUntilRoot() }) {
                Text("Volver al inicio")
            }
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }

}