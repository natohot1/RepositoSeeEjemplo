package com.example.seeejemplo;

import android.provider.BaseColumns;

public class Peso_edad {
    public static String ID_MEDI2 = BaseColumns._ID;
    public static String CN_edad = "edad";
    public static String CN_peso = "peso";
    public static String CN_peso1 = "peso1";
    public static String CN_edad_num ="edad_num";

    public Peso_edad(String ID_MEDI2, String CN_edad, String CN_peso, String CN_peso1, String CN_edad_num) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_edad = CN_edad;
        this.CN_peso = CN_peso;
        this.CN_peso1 = CN_peso1;
        this.CN_edad_num = CN_edad_num;
    }

    //<editor-fold desc="GETTER - SETTER">
    public String getID_MEDI2() {
        return ID_MEDI2;
    }

    public void setID_MEDI2(String ID_MEDI2) {
        this.ID_MEDI2 = ID_MEDI2;
    }

    public String getCN_edad() {
        return CN_edad;
    }

    public void setCN_edad(String CN_edad) {
        this.CN_edad = CN_edad;
    }

    public String getCN_peso() {
        return CN_peso;
    }

    public void setCN_peso(String CN_peso) {
        this.CN_peso = CN_peso;
    }

    public String getCN_peso1() {
        return CN_peso1;
    }

    public void setCN_peso1(String CN_peso1) {
        this.CN_peso1 = CN_peso1;
    }

    public String getCN_edad_num() {
        return CN_edad_num;
    }

    public void setCN_edad_num(String CN_edad_num) {
        this.CN_edad_num = CN_edad_num;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peso_edad)) return false;

        Peso_edad peso_edad = (Peso_edad) o;

        if (!ID_MEDI2.equals(peso_edad.ID_MEDI2)) return false;
        if (CN_edad != null ? !CN_edad.equals(peso_edad.CN_edad) : peso_edad.CN_edad != null)
            return false;
        if (CN_peso != null ? !CN_peso.equals(peso_edad.CN_peso) : peso_edad.CN_peso != null)
            return false;
        if (CN_peso1 != null ? !CN_peso1.equals(peso_edad.CN_peso1) : peso_edad.CN_peso1 != null)
            return false;
        return CN_edad_num != null ? CN_edad_num.equals(peso_edad.CN_edad_num) : peso_edad.CN_edad_num == null;

    }

    @Override
    public int hashCode() {
        int result = ID_MEDI2.hashCode();
        result = 31 * result + (CN_edad != null ? CN_edad.hashCode() : 0);
        result = 31 * result + (CN_peso != null ? CN_peso.hashCode() : 0);
        result = 31 * result + (CN_peso1 != null ? CN_peso1.hashCode() : 0);
        result = 31 * result + (CN_edad_num != null ? CN_edad_num.hashCode() : 0);
        return result;
    }
}
