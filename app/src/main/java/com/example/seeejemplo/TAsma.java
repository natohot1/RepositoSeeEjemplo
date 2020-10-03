package com.example.seeejemplo;

import android.provider.BaseColumns;

public class TAsma {
    public static String ID_MEDI2 = BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_leve = "leve";
    public static String CN_cada = "cada";
    public static String CN_moderado = "moderado";
    public static String CN_cadaM = "cadaM";
    public static String CN_severo = "severo";
    public static String CN_cadaS = "cadaS";
    public static String CN_bronquilitis = "bronquilitis";
    public static String CN_cadaBron = "cadaBron";

    public TAsma(String ID_MEDI2, String CN_medicamento, String CN_leve, String CN_cada, String CN_moderado, String CN_cadaM, String CN_severo, String CN_cadaS,
                 String CN_bronquilitis, String CN_cadaBron) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_medicamento = "medicamento";
        this.CN_leve = "leve";
        this.CN_cada = "cada";
        this.CN_moderado = "moderado";
        this.CN_cadaM = "cadaM";
        this.CN_severo = "severo";
        this.CN_cadaS = "cadaS";
        this.CN_bronquilitis = "bronquilitis";
        this.CN_cadaBron = "cadaBron";

    }

    public static String getIdMedi2() {
        return ID_MEDI2;
    }

    public static void setIdMedi2(String idMedi2) {
        ID_MEDI2 = idMedi2;
    }

    public static String getCN_medicamento() {
        return CN_medicamento;
    }

    public static void setCN_medicamento(String CN_medicamento) {
        TAsma.CN_medicamento = CN_medicamento;
    }

    public static String getCN_leve() {
        return CN_leve;
    }

    public static void setCN_leve(String CN_leve) {
        TAsma.CN_leve = CN_leve;
    }

    public static String getCN_cada() {
        return CN_cada;
    }

    public static void setCN_cada(String CN_cada) {
        TAsma.CN_cada = CN_cada;
    }

    public static String getCN_moderado() {
        return CN_moderado;
    }

    public static void setCN_moderado(String CN_moderado) {
        TAsma.CN_moderado = CN_moderado;
    }

    public static String getCN_cadaM() {
        return CN_cadaM;
    }

    public static void setCN_cadaM(String CN_cadaM) {
        TAsma.CN_cadaM = CN_cadaM;
    }

    public static String getCN_severo() {
        return CN_severo;
    }

    public static void setCN_severo(String CN_severo) {
        TAsma.CN_severo = CN_severo;
    }

    public static String getCN_cadaS() {
        return CN_cadaS;
    }

    public static void setCN_cadaS(String CN_cadaS) {
        TAsma.CN_cadaS = CN_cadaS;
    }

    public static String getCN_bronquilitis() {
        return CN_bronquilitis;
    }

    public static void setCN_bronquilitis(String CN_bronquilitis) {
        TAsma.CN_bronquilitis = CN_bronquilitis;
    }

    public static String getCN_cadaBron() {
        return CN_cadaBron;
    }

    public static void setCN_cadaBron(String CN_cadaBron) {
        TAsma.CN_cadaBron = CN_cadaBron;
    }

    public static String getCN_bronquilitisMod() {
        return CN_bronquilitisMod;
    }

    public static void setCN_bronquilitisMod(String CN_bronquilitisMod) {
        TAsma.CN_bronquilitisMod = CN_bronquilitisMod;
    }

    public static String getCN_cadaBronMod() {
        return CN_cadaBronMod;
    }

    public static void setCN_cadaBronMod(String CN_cadaBronMod) {
        TAsma.CN_cadaBronMod = CN_cadaBronMod;
    }

    public static String getCN_bronquioliitisSev() {
        return CN_bronquioliitisSev;
    }

    public static void setCN_bronquioliitisSev(String CN_bronquioliitisSev) {
        TAsma.CN_bronquioliitisSev = CN_bronquioliitisSev;
    }

    public static String getCN_cadaBronSev() {
        return CN_cadaBronSev;
    }

    public static void setCN_cadaBronSev(String CN_cadaBronSev) {
        TAsma.CN_cadaBronSev = CN_cadaBronSev;
    }

    public static String CN_bronquilitisMod = "bronquilitisMod";
    public static String CN_cadaBronMod = "cadaBronMod";
    public static String CN_bronquioliitisSev = "bronquioliitisSev";
    public static String CN_cadaBronSev = "cadaBronSev";
}
