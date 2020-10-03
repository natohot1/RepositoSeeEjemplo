package com.example.seeejemplo;

import android.provider.BaseColumns;

import java.util.Objects;

public class AsmaTabla {

    public String _id, medicamento , leve, cada, moderado, cadaM, severo , cadaS, bronquilitis, cadaBron, bronquilitisMod , cadaBronMod , bronquioliitisSev , cadaBronSev;
    public static final String DATOS_TABLA_ASMA ="asma";
    public static final String STRING_TYPE="text";
    public static final String INT_TYPE="integer";


    public AsmaTabla(String _id, String medicamento, String leve, String cada, String moderado, String cadaM, String severo, String cadaS, String bronquilitis, String cadaBron, String bronquilitisMod, String cadaBronMod, String bronquioliitisSev, String cadaBronSev) {
        this._id = _id;
        this.medicamento = medicamento;
        this.leve = leve;
        this.cada = cada;
        this.moderado = moderado;
        this.cadaM = cadaM;
        this.severo = severo;
        this.cadaS = cadaS;
        this.bronquilitis = bronquilitis;
        this.cadaBron = cadaBron;
        this.bronquilitisMod = bronquilitisMod;
        this.cadaBronMod = cadaBronMod;
        this.bronquioliitisSev = bronquioliitisSev;
        this.cadaBronSev = cadaBronSev;
    }

    public static String ID_MEDI2= BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_leve = "leve";
    public static String CN_cada = "cada";
    public static String CN_moderado = "moderado";
    public static String CN_cadaM = "cadaM";
    public static String CN_severo = "severo";
    public static String CN_cadaS = "cadaS";
    public static String CN_bronquilitis = "bronquilitis";
    public static String CN_cadaBron = "cadaBron";
    public static String CN_bronquilitisMod = "bronquilitisMod";
    public static String CN_cadaBronMod = "cadaBronMod";
    public static String CN_jbronquioliitisSev = "bronquioliitisSev";
    public static String CN_cadaBronSev = "cadaBronSev";

    public AsmaTabla() {
    }

    public static String getDatosTablaAsma() {
        return DATOS_TABLA_ASMA;
    }

    public static String getStringType() {
        return STRING_TYPE;
    }

    public static String getIntType() {
        return INT_TYPE;
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
        AsmaTabla.CN_medicamento = CN_medicamento;
    }

    public static String getCN_leve() {
        return CN_leve;
    }

    public static void setCN_leve(String CN_leve) {
        AsmaTabla.CN_leve = CN_leve;
    }

    public static String getCN_cada() {
        return CN_cada;
    }

    public static void setCN_cada(String CN_cada) {
        AsmaTabla.CN_cada = CN_cada;
    }

    public static String getCN_moderado() {
        return CN_moderado;
    }

    public static void setCN_moderado(String CN_moderado) {
        AsmaTabla.CN_moderado = CN_moderado;
    }

    public static String getCN_cadaM() {
        return CN_cadaM;
    }

    public static void setCN_cadaM(String CN_cadaM) {
        AsmaTabla.CN_cadaM = CN_cadaM;
    }

    public static String getCN_severo() {
        return CN_severo;
    }

    public static void setCN_severo(String CN_severo) {
        AsmaTabla.CN_severo = CN_severo;
    }

    public static String getCN_cadaS() {
        return CN_cadaS;
    }

    public static void setCN_cadaS(String CN_cadaS) {
        AsmaTabla.CN_cadaS = CN_cadaS;
    }

    public static String getCN_bronquilitis() {
        return CN_bronquilitis;
    }

    public static void setCN_bronquilitis(String CN_bronquilitis) {
        AsmaTabla.CN_bronquilitis = CN_bronquilitis;
    }

    public static String getCN_cadaBron() {
        return CN_cadaBron;
    }

    public static void setCN_cadaBron(String CN_cadaBron) {
        AsmaTabla.CN_cadaBron = CN_cadaBron;
    }

    public static String getCN_bronquilitisMod() {
        return CN_bronquilitisMod;
    }

    public static void setCN_bronquilitisMod(String CN_bronquilitisMod) {
        AsmaTabla.CN_bronquilitisMod = CN_bronquilitisMod;
    }

    public static String getCN_cadaBronMod() {
        return CN_cadaBronMod;
    }

    public static void setCN_cadaBronMod(String CN_cadaBronMod) {
        AsmaTabla.CN_cadaBronMod = CN_cadaBronMod;
    }

    public static String getCN_jbronquioliitisSev() {
        return CN_jbronquioliitisSev;
    }

    public static void setCN_jbronquioliitisSev(String CN_jbronquioliitisSev) {
        AsmaTabla.CN_jbronquioliitisSev = CN_jbronquioliitisSev;
    }

    public static String getCN_cadaBronSev() {
        return CN_cadaBronSev;
    }

    public static void setCN_cadaBronSev(String CN_cadaBronSev) {
        AsmaTabla.CN_cadaBronSev = CN_cadaBronSev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsmaTabla)) return false;
        AsmaTabla asmaTabla = (AsmaTabla) o;
        return Objects.equals(_id, asmaTabla._id) &&
                Objects.equals(medicamento, asmaTabla.medicamento) &&
                Objects.equals(leve, asmaTabla.leve) &&
                Objects.equals(cada, asmaTabla.cada) &&
                Objects.equals(moderado, asmaTabla.moderado) &&
                Objects.equals(cadaM, asmaTabla.cadaM) &&
                Objects.equals(severo, asmaTabla.severo) &&
                Objects.equals(cadaS, asmaTabla.cadaS) &&
                Objects.equals(bronquilitis, asmaTabla.bronquilitis) &&
                Objects.equals(cadaBron, asmaTabla.cadaBron) &&
                Objects.equals(bronquilitisMod, asmaTabla.bronquilitisMod) &&
                Objects.equals(cadaBronMod, asmaTabla.cadaBronMod) &&
                Objects.equals(bronquioliitisSev, asmaTabla.bronquioliitisSev) &&
                Objects.equals(cadaBronSev, asmaTabla.cadaBronSev);
    }

    @Override
    public int hashCode() {

        return Objects.hash(_id, medicamento, leve, cada, moderado, cadaM, severo, cadaS, bronquilitis, cadaBron, bronquilitisMod, cadaBronMod, bronquioliitisSev, cadaBronSev);
    }

}
