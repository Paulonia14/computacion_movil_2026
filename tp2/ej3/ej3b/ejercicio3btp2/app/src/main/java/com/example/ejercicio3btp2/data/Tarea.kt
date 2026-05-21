package com.example.ejercicio3btp2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val titulo:String
)