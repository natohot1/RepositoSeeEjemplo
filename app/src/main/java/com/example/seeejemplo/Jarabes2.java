package com.example.seeejemplo;

import android.provider.BaseColumns;

public class Jarabes2 {
    public static String ID_MEDI2 = BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_primero = "primero";
    public static String CN_segundo = "segundo";
    public static String CN_tercero = "tercero";
    public static String CN_cuarto = "cuarto";
    public static String CN_quinto = "quinto";
    public static String CN_indicaciones = "indicaciones";

    //<editor-fold desc="GETTER - STTER">
    public String getID_MEDI2() {
        return ID_MEDI2;
    }

    public void setID_MEDI2(String ID_MEDI2) {
        this.ID_MEDI2 = ID_MEDI2;
    }

    public String getCN_medicamento() {
        return CN_medicamento;
    }

    public void setCN_medicamento(String CN_medicamento) {
        this.CN_medicamento = CN_medicamento;
    }

    public String getCN_primero() {
        return CN_primero;
    }

    public void setCN_primero(String CN_primero) {
        this.CN_primero = CN_primero;
    }

    public String getCN_segundo() {
        return CN_segundo;
    }

    public void setCN_segundo(String CN_segundo) {
        this.CN_segundo = CN_segundo;
    }

    public String getCN_tercero() {
        return CN_tercero;
    }

    public void setCN_tercero(String CN_tercero) {
        this.CN_tercero = CN_tercero;
    }

    public String getCN_cuarto() {
        return CN_cuarto;
    }

    public void setCN_cuarto(String CN_cuarto) {
        this.CN_cuarto = CN_cuarto;
    }

    public String getCN_quinto() {
        return CN_quinto;
    }

    public void setCN_quinto(String CN_quinto) {
        this.CN_quinto = CN_quinto;
    }

    public String getCN_indicaciones() {
        return CN_indicaciones;
    }

    public void setCN_indicaciones(String CN_indicaciones) {
        this.CN_indicaciones = CN_indicaciones;
    }
    //</editor-fold>

    public Jarabes2(String ID_MEDI2, String CN_medicamento, String CN_primero, String CN_segundo, String CN_tercero, String CN_cuarto, String CN_quinto, String CN_indicaciones) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_medicamento = CN_medicamento;
        this.CN_primero = CN_primero;
        this.CN_segundo = CN_segundo;
        this.CN_tercero = CN_tercero;
        this.CN_cuarto = CN_cuarto;
        this.CN_quinto = CN_quinto;
        this.CN_indicaciones = CN_indicaciones;
    }



}
