package com.example.myapplication.controladores;

import com.example.myapplication.modelos.Libro;

import java.util.List;

public interface InterfaceLibroBD {

    Libro elemento(int id);
    Libro elemento (String title);

    List<Libro> lista();

    void agregar (Libro book);
    void actualizar(int id, Libro Book);

    void borrar(int id);

}
