package com.example.seeejemplo;

import android.provider.BaseColumns;

public class Medicamento {
    public static final String DATOS_TABLA_MEDICAMENTOS ="medicamentos2";
    public static final String STRING_TYPE="text";
    public static final String INT_TYPE="integer";



    public static String ID_MEDI2= BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_dosisAddia = "dosisAddia";
    public static String CN_DosisP = "dosisPed";
    public static String CN_DosisP1 = "dosisPed1";
    public static String CN_DosisP2 = "dosisPed2";
    public static String CN_nebulizada = "nebulizada";
    public static String CN_cada = "cada";
    public static String CN_doMaxNi = "doMaxNi";
    public static String CN_boJarabe5 = "boJarabe5";
    public static String CN_jarabe5 = "jarabe5";
    public static String CN_comprimidos = "comprimidos";
    public static String CN_gotas = "gotas";
    public static String CN_sobres = "sobres";
    public static String CN_SolInhalador = "SolInhalador";
    public static String CN_contraindicaciones = "contraindicaciones";
    public static String CN_precauciones = "precauciones";
    public static String CN_secundarios = "secundarios";
    public static String CN_indicaciones = "indicaciones";
    public static String CN_nobrecom ="comer";

    public Medicamento() {
    }

    public Medicamento(String ID_MEDI2, String CN_medicamento, String CN_dosisAddia,
                       String CN_DosisP, String CN_DosisP1, String CN_DosisP2,
                       String CN_nebulizada, String CN_cada, String CN_doMaxNi,
                       String CN_boJarabe5, String CN_jarabe5, String CN_comprimidos,
                       String CN_gotas, String CN_sobres, String CN_SolInhalador,
                       String CN_contraindicaciones, String CN_precauciones,
                       String CN_secundarios, String CN_indicaciones, String CN_nobrecom) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_medicamento = CN_medicamento;
        this.CN_dosisAddia = CN_dosisAddia;
        this.CN_DosisP = CN_DosisP;
        this.CN_DosisP1 = CN_DosisP1;
        this.CN_DosisP2 = CN_DosisP2;
        this.CN_nebulizada = CN_nebulizada;
        this.CN_cada = CN_cada;
        this.CN_doMaxNi = CN_doMaxNi;
        this.CN_boJarabe5 = CN_boJarabe5;
        this.CN_jarabe5 = CN_jarabe5;
        this.CN_comprimidos = CN_comprimidos;
        this.CN_gotas = CN_gotas;
        this.CN_sobres = CN_sobres;
        this.CN_SolInhalador = CN_SolInhalador;
        this.CN_contraindicaciones = CN_contraindicaciones;
        this.CN_precauciones = CN_precauciones;
        this.CN_secundarios = CN_secundarios;
        this.CN_indicaciones = CN_indicaciones;
        this.CN_nobrecom = CN_nobrecom;
    }

    //<editor-fold desc="GETTER-SETTER">
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

    public String getCN_dosisAddia() {
        return CN_dosisAddia;
    }

    public void setCN_dosisAddia(String CN_dosisAddia) {
        this.CN_dosisAddia = CN_dosisAddia;
    }

    public String getCN_DosisP() {
        return CN_DosisP;
    }

    public void setCN_DosisP(String CN_DosisP) {
        this.CN_DosisP = CN_DosisP;
    }

    public String getCN_DosisP1() {
        return CN_DosisP1;
    }

    public void setCN_DosisP1(String CN_DosisP1) {
        this.CN_DosisP1 = CN_DosisP1;
    }

    public String getCN_DosisP2() {
        return CN_DosisP2;
    }

    public void setCN_DosisP2(String CN_DosisP2) {
        this.CN_DosisP2 = CN_DosisP2;
    }

    public String getCN_nebulizada() {
        return CN_nebulizada;
    }

    public void setCN_nebulizada(String CN_nebulizada) {
        this.CN_nebulizada = CN_nebulizada;
    }

    public String getCN_cada() {
        return CN_cada;
    }

    public void setCN_cada(String CN_cada) {
        this.CN_cada = CN_cada;
    }

    public String getCN_doMaxNi() {
        return CN_doMaxNi;
    }

    public void setCN_doMaxNi(String CN_doMaxNi) {
        this.CN_doMaxNi = CN_doMaxNi;
    }

    public String getCN_doMaxAd() {
        return CN_boJarabe5;
    }

    public void setCN_doMaxAd(String CN_boJarabe5) {
        this.CN_boJarabe5 = CN_boJarabe5;
    }

    public String getCN_jarabe5() {
        return CN_jarabe5;
    }

    public void setCN_jarabe5(String CN_jarabe5) {
        this.CN_jarabe5 = CN_jarabe5;
    }

    public String getCN_comprimidos() {
        return CN_comprimidos;
    }

    public void setCN_comprimidos(String CN_comprimidos) {
        this.CN_comprimidos = CN_comprimidos;
    }

    public String getCN_gotas() {
        return CN_gotas;
    }

    public void setCN_gotas(String CN_gotas) {
        this.CN_gotas = CN_gotas;
    }

    public String getCN_sobres() {
        return CN_sobres;
    }

    public void setCN_sobres(String CN_sobres) {
        this.CN_sobres = CN_sobres;
    }

    public String getCN_SolInhalador() {
        return CN_SolInhalador;
    }

    public void setCN_SolInhalador(String CN_SolInhalador) {
        this.CN_SolInhalador = CN_SolInhalador;
    }

    public String getCN_contraindicaciones() {
        return CN_contraindicaciones;
    }

    public void setCN_contraindicaciones(String CN_contraindicaciones) {
        this.CN_contraindicaciones = CN_contraindicaciones;
    }

    public String getCN_precauciones() {
        return CN_precauciones;
    }

    public void setCN_precauciones(String CN_precauciones) {
        this.CN_precauciones = CN_precauciones;
    }

    public String getCN_secundarios() {
        return CN_secundarios;
    }

    public void setCN_secundarios(String CN_secundarios) {
        this.CN_secundarios = CN_secundarios;
    }

    public String getCN_indicaciones() {
        return CN_indicaciones;
    }

    public void setCN_indicaciones(String CN_indicaciones) {
        this.CN_indicaciones = CN_indicaciones;
    }

    public String getCN_nobrecom() {
        return CN_nobrecom;
    }

    public void setCN_nobrecom(String CN_nobrecom) {
        this.CN_nobrecom = CN_nobrecom;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicamento)) return false;

        Medicamento that = (Medicamento) o;

        if (!ID_MEDI2.equals(that.ID_MEDI2)) return false;
        if (!CN_medicamento.equals(that.CN_medicamento)) return false;
        if (CN_dosisAddia != null ? !CN_dosisAddia.equals(that.CN_dosisAddia) : that.CN_dosisAddia != null)
            return false;
        if (CN_DosisP != null ? !CN_DosisP.equals(that.CN_DosisP) : that.CN_DosisP != null)
            return false;
        if (CN_DosisP1 != null ? !CN_DosisP1.equals(that.CN_DosisP1) : that.CN_DosisP1 != null)
            return false;
        if (CN_DosisP2 != null ? !CN_DosisP2.equals(that.CN_DosisP2) : that.CN_DosisP2 != null)
            return false;
        if (CN_nebulizada != null ? !CN_nebulizada.equals(that.CN_nebulizada) : that.CN_nebulizada != null)
            return false;
        if (CN_cada != null ? !CN_cada.equals(that.CN_cada) : that.CN_cada != null) return false;
        if (CN_doMaxNi != null ? !CN_doMaxNi.equals(that.CN_doMaxNi) : that.CN_doMaxNi != null)
            return false;
        if (CN_boJarabe5 != null ? !CN_boJarabe5.equals(that.CN_boJarabe5) : that.CN_boJarabe5 != null)
            return false;
        if (CN_jarabe5 != null ? !CN_jarabe5.equals(that.CN_jarabe5) : that.CN_jarabe5 != null)
            return false;
        if (CN_comprimidos != null ? !CN_comprimidos.equals(that.CN_comprimidos) : that.CN_comprimidos != null)
            return false;
        if (CN_gotas != null ? !CN_gotas.equals(that.CN_gotas) : that.CN_gotas != null)
            return false;
        if (CN_sobres != null ? !CN_sobres.equals(that.CN_sobres) : that.CN_sobres != null)
            return false;
        if (CN_SolInhalador != null ? !CN_SolInhalador.equals(that.CN_SolInhalador) : that.CN_SolInhalador != null)
            return false;
        if (CN_contraindicaciones != null ? !CN_contraindicaciones.equals(that.CN_contraindicaciones) : that.CN_contraindicaciones != null)
            return false;
        if (CN_precauciones != null ? !CN_precauciones.equals(that.CN_precauciones) : that.CN_precauciones != null)
            return false;
        if (CN_secundarios != null ? !CN_secundarios.equals(that.CN_secundarios) : that.CN_secundarios != null)
            return false;
        if (CN_indicaciones != null ? !CN_indicaciones.equals(that.CN_indicaciones) : that.CN_indicaciones != null)
            return false;
        return CN_nobrecom != null ? CN_nobrecom.equals(that.CN_nobrecom) : that.CN_nobrecom == null;

    }

    @Override
    public int hashCode() {
        int result = ID_MEDI2.hashCode();
        result = 31 * result + CN_medicamento.hashCode();
        result = 31 * result + (CN_dosisAddia != null ? CN_dosisAddia.hashCode() : 0);
        result = 31 * result + (CN_DosisP != null ? CN_DosisP.hashCode() : 0);
        result = 31 * result + (CN_DosisP1 != null ? CN_DosisP1.hashCode() : 0);
        result = 31 * result + (CN_DosisP2 != null ? CN_DosisP2.hashCode() : 0);
        result = 31 * result + (CN_nebulizada != null ? CN_nebulizada.hashCode() : 0);
        result = 31 * result + (CN_cada != null ? CN_cada.hashCode() : 0);
        result = 31 * result + (CN_doMaxNi != null ? CN_doMaxNi.hashCode() : 0);
        result = 31 * result + (CN_boJarabe5 != null ? CN_boJarabe5.hashCode() : 0);
        result = 31 * result + (CN_jarabe5 != null ? CN_jarabe5.hashCode() : 0);
        result = 31 * result + (CN_comprimidos != null ? CN_comprimidos.hashCode() : 0);
        result = 31 * result + (CN_gotas != null ? CN_gotas.hashCode() : 0);
        result = 31 * result + (CN_sobres != null ? CN_sobres.hashCode() : 0);
        result = 31 * result + (CN_SolInhalador != null ? CN_SolInhalador.hashCode() : 0);
        result = 31 * result + (CN_contraindicaciones != null ? CN_contraindicaciones.hashCode() : 0);
        result = 31 * result + (CN_precauciones != null ? CN_precauciones.hashCode() : 0);
        result = 31 * result + (CN_secundarios != null ? CN_secundarios.hashCode() : 0);
        result = 31 * result + (CN_indicaciones != null ? CN_indicaciones.hashCode() : 0);
        result = 31 * result + (CN_nobrecom != null ? CN_nobrecom.hashCode() : 0);
        return result;
    }
}
