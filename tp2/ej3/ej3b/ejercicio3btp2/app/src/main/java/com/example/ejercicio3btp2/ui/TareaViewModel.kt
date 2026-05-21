package com.example.ejercicio3btp2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio3btp2.data.Tarea
import com.example.ejercicio3btp2.data.TareaDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TareaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = TareaDatabase.getDatabase(application).tareaDao()

    val tareas: Flow<List<Tarea>> = dao.obtenerTareas()

    fun agregarTareas(texto: String) {

        viewModelScope.launch {
            dao.insertar(Tarea(titulo = texto))
        }
    }

    fun eliminarTarea(tarea: Tarea) {

        viewModelScope.launch {
            dao.eliminar(tarea)
        }
    }
}