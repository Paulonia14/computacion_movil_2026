package com.compa.ej_2b.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.compa.ej_2b.utilidades.Utilidades;

public class conexionSQLite extends SQLiteOpenHelper {

    Context context;

    public conexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.crear_tabla_cliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VA, int VN) {
        db.execSQL("DROP TABLE IF EXISTS t_cliente");
        onCreate(db);
    }
}
