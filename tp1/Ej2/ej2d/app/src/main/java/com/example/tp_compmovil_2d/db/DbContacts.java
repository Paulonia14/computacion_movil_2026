package com.example.tp_compmovil_2d.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tp_compmovil_2d.entities.Contact;

import java.util.ArrayList;

public class DbContacts extends DbHelper {

    private Context context;

    public DbContacts(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insert(String name, String phone, String email){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("phone", phone);
            values.put("email", email);
            id = db.insert(TABLE_CONTACTS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public boolean update(int id, String name, String phone, String email) {
        boolean success = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("phone", phone);
            values.put("email", email);

            int result = db.update(TABLE_CONTACTS, values, "id = ?", new String[]{String.valueOf(id)});

            success = result > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }

        return success;
    }

    public boolean delete(int id) {
        boolean success = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            int result = db.delete(TABLE_CONTACTS, "id = ?", new String[]{String.valueOf(id)});
            success = result > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }

        return success;
    }

    public ArrayList<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contact contact;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        if (cursor.moveToFirst()){
            do {
                contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contacts.add(contact);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return contacts;
    }

    public Contact getById(int id) {
        Contact contact = null;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursor.moveToFirst()){
            contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
        } else {
            return null;
        }

        return contact;
    }
}
