package com.compa.ktorbasic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compa.ktorbasic.network.NetworkUtils.httpClient
import com.compa.ktorbasic.network.model.ApiResponse
import com.compa.ktorbasic.network.model.Hero
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource

import ktorbasic_ej4.composeapp.generated.resources.Res
import ktorbasic_ej4.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var superheroName by remember {mutableStateOf("")}
        var superheroList by remember {mutableStateOf<List<Hero>>(emptyList())}
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                TextField(value = superheroName, onValueChange = {superheroName = it})
                Button(onClick = {getSuperheroList(superheroName){ superheroList = it} }){  //Le asigna a superheroList la respuesta del backend (it)
                    Text("Load")
                }
            }
            //Lista
            LazyColumn {
                items(superheroList){ hero ->
                    Text(hero.name)
                }
            }

        }
    }
}

fun getSuperheroList(superheroName: String, onSuccessResponse:(List<Hero>) -> Unit){
    val url = "https://superheroapi.com/api/9a6af887d22e80fb1dccec52b31f96de/search/$superheroName"
    if (superheroName.isBlank()) return
    CoroutineScope(Dispatchers.IO).launch {
        val response = httpClient.get(url).body<ApiResponse>()
        withContext(Dispatchers.Main) {
            onSuccessResponse(response.results)
        }
    }
}