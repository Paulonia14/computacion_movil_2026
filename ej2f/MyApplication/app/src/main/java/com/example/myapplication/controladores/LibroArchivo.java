package com.example.myapplication.controladores;


import android.content.Context;

import com.example.myapplication.modelos.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibroArchivo {

    private static final String FILE_NAME = "libros.dat";

    public static void sobrescribirLibros(Context context, List<Libro> lista) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);

            for (Libro libro : lista){
                dos.writeInt(libro.getId());
                dos.writeUTF(libro.getTitulo());
                dos.writeUTF(libro.getSubtitulo());
                dos.writeUTF(libro.getIsbn());
                dos.writeUTF(libro.getAutor());
                dos.writeInt(libro.getAnioPublicacion());
                dos.writeDouble(libro.getPrecio());
            }

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarLibro(Context context, Libro libro) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(libro.getId());
            dos.writeUTF(libro.getTitulo());
            dos.writeUTF(libro.getSubtitulo());
            dos.writeUTF(libro.getIsbn());
            dos.writeUTF(libro.getAutor());
            dos.writeInt(libro.getAnioPublicacion());
            dos.writeDouble(libro.getPrecio());

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Libro> leerLibros(Context context) {
        List<Libro> lista = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            DataInputStream dis = new DataInputStream(fis);

            while (dis.available() > 0) {
                Libro l = new Libro();

                l.setId(dis.readInt());
                l.setTitulo(dis.readUTF());
                l.setSubtitulo(dis.readUTF());
                l.setIsbn(dis.readUTF());
                l.setAutor(dis.readUTF());
                l.setAnioPublicacion(dis.readInt());
                l.setPrecio(dis.readDouble());

                lista.add(l);
            }

            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}