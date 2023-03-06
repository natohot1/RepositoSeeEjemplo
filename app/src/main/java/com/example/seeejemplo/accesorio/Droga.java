package com.example.seeejemplo.accesorio;

import android.provider.BaseColumns;

public class Droga {

    public Droga() {
    }

    public static String ID_MEDI2= BaseColumns._ID;
    public static String CN_nombre = "nombre";
    public static String CN_comercial = "comercial";
    public static String CN_cantidad_mg = "cantidad_mg";
    public static String CN_cantidad_ml = "cantidad_ml";
    public static String CN_obser = "obser";
    public static String Tabla_droga = "droga";


//<editor-fold desc="GETTER -SETER">

    public static String getIdMedi2() {
        return ID_MEDI2;
    }

    public static void setIdMedi2(String idMedi2) {
        ID_MEDI2 = idMedi2;
    }

    public static String getCN_nombre() {
        return CN_nombre;
    }

    public static void setCN_nombre(String CN_nombre) {
        Droga.CN_nombre = CN_nombre;
    }

    public static String getCN_comercial() {
        return CN_comercial;
    }

    public static void setCN_comercial(String CN_comercial) {
        Droga.CN_comercial = CN_comercial;
    }

    public static String getCN_cantidad_mg() {
        return CN_cantidad_mg;
    }

    public static void setCN_cantidad_mg(String CN_cantidad_mg) {
        Droga.CN_cantidad_mg = CN_cantidad_mg;
    }

    public static String getCN_cantidad_ml() {
        return CN_cantidad_ml;
    }

    public static void setCN_cantidad_ml(String CN_cantidad_ml) {
        Droga.CN_cantidad_ml = CN_cantidad_ml;
    }
    public static String getCN_obser() {
        return CN_obser;
    }

    public static void setCN_obser(String CN_obser) {
        Droga.CN_obser = CN_obser;
    }
    //</editor-fold>

    public Droga(String ID_MEDI2, String CN_nombre, String CN_comercial, String CN_cantidad_mg, String CN_cantidad_ml, String CN_obser){
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_nombre = CN_nombre;
        this.CN_comercial = CN_comercial;
        this.CN_cantidad_mg = CN_cantidad_mg;
        this.CN_cantidad_ml = CN_cantidad_ml;
        this.CN_obser = CN_obser;
    }
}
