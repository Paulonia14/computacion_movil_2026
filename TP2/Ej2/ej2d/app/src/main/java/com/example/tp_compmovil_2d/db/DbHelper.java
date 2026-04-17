package com.example.tp_compmovil_2d.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "agenda.db";
    public static final String TABLE_CONTACTS = "t_contacts";
    private static final String TCONTACTS_ID = "id INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TCONTACTS_NAME = "name TEXT NOT NULL";
    private static final String TCONTACTS_PHONE = "phone TEXT NOT NULL";
    private static final String TCONTACTS_EMAIL = "email TEXT";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" +
                TCONTACTS_ID + "," +
                TCONTACTS_NAME + "," +
                TCONTACTS_PHONE + "," +
                TCONTACTS_EMAIL +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CONTACTS);
        onCreate(db);
    }
}
