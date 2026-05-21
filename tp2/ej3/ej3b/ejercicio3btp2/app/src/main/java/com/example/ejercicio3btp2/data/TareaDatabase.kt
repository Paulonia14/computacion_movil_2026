package com.example.ejercicio3btp2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class], version = 1)
abstract class TareaDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao

    companion object {

        @Volatile
        private var INSTANCIA: TareaDatabase? = null

        fun getDatabase(context: Context): TareaDatabase {

            return INSTANCIA ?: synchronized(this) {

                Room.databaseBuilder(
                    context.applicationContext,
                    TareaDatabase::class.java,
                    "tareas_db"
                ).build().also { INSTANCIA = it }
            }
        }
    }
}