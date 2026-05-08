package com.compa.navigator.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class BottomBarScreen : Screen {  // Clase contenedora de toda la informacion

    @OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class)  // Para que no llore el TopAppBar pongo el de ExperimentalMaterial3Api
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable (
                    it,
                    listOf(HomeTab, FavTab, ProfileTab)
                )
            }
        ){
            // Esqueleto de la pantalla
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(it.current.options.title) },
                        actions = {
                            IconButton(onClick = {
                                navigator.popUntilRoot() // Vuelve a la pantalla principal
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Ir al inicio"
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val tabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = { Text(HomeTab.options.title) },
                            icon = {
                                Icon(painter = HomeTab.options.icon!!, contentDescription = null)
                            },
                            onClick = { tabNavigator.current = HomeTab }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == FavTab.key,
                            label = { Text(FavTab.options.title) },
                            icon = {
                                Icon(painter = FavTab.options.icon!!, contentDescription = null)
                            },
                            onClick = { tabNavigator.current = FavTab }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProfileTab.key,
                            label = { Text(ProfileTab.options.title) },
                            icon = {
                                Icon(painter = ProfileTab.options.icon!!, contentDescription = null)
                            },
                            onClick = { tabNavigator.current = ProfileTab }
                        )
                    }
                }
            ){ //content
                CurrentTab()
            }
        }
    }

}