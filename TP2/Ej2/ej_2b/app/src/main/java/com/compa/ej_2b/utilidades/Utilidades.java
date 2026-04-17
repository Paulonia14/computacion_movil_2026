package com.compa.ej_2b.utilidades;

public class Utilidades {
    public static final String TABLA_CLIENTE = "t_cliente";
    public static final String CAMPO_ID = "id_cliente";
    public static final String CAMPO_NOM = "nom_cliente";
    public static final String CAMPO_APE = "ape_cliente";
    public static final String CAMPO_DOC = "doc_cliente";

    public static final String crear_tabla_cliente = "CREATE TABLE " + TABLA_CLIENTE + " (" +
            CAMPO_ID + " INTEGER, " + CAMPO_NOM + " TEXT, " +
            CAMPO_APE + " TEXT, " + CAMPO_DOC + " TEXT)";

}
