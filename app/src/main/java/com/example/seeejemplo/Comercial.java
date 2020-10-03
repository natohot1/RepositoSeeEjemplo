package com.example.seeejemplo;

import android.provider.BaseColumns;

public class Comercial {
    public static String ID_MEDI2= BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_nobrecom = "comer";
    public static String CN_presentacion = "presentacion";

    //<editor-fold desc="GETTER -SETER">
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

    public String getCN_nobrecom() {
        return CN_nobrecom;
    }

    public void setCN_nobrecom(String CN_nobrecom) {
        this.CN_nobrecom = CN_nobrecom;
    }

    public String getCN_presentacion() {
        return CN_presentacion;
    }

    public void setCN_presentacion(String CN_presentacion) {
        this.CN_presentacion = CN_presentacion;
    }
    //</editor-fold>

    public Comercial(String ID_MEDI2, String CN_medicamento, String CN_nobrecom, String CN_presentacion) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_medicamento = CN_medicamento;
        this.CN_nobrecom=CN_nobrecom;
        this.CN_presentacion=CN_presentacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comercial)) return false;

        Comercial comercial = (Comercial) o;

        if (!ID_MEDI2.equals(comercial.ID_MEDI2)) return false;
        if (!CN_medicamento.equals(comercial.CN_medicamento)) return false;
        if (CN_nobrecom != null ? !CN_nobrecom.equals(comercial.CN_nobrecom) : comercial.CN_nobrecom != null)
            return false;
        return CN_presentacion != null ? CN_presentacion.equals(comercial.CN_presentacion) : comercial.CN_presentacion == null;

    }

    @Override
    public int hashCode() {
        int result = ID_MEDI2.hashCode();
        result = 31 * result + CN_medicamento.hashCode();
        result = 31 * result + (CN_nobrecom != null ? CN_nobrecom.hashCode() : 0);
        result = 31 * result + (CN_presentacion != null ? CN_presentacion.hashCode() : 0);
        return result;
    }
}
