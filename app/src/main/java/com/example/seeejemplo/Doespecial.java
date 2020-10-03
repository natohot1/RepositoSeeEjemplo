package com.example.seeejemplo;

import android.provider.BaseColumns;

public class Doespecial {
    public static String ID_MEDI2= BaseColumns._ID;
    public static String CN_medicamento = "medicamento";
    public static String CN_control ="control";
    public static String CN_pri_do = "pri_do";
    public static String CN_seg_do = "seg_do";
    public static String CN_ed_pri = "ed_pri";
    public static String CN_ed_seg = "ed_seg";
    public static String CN_ext = "ext";
    public static String CN_pri_do1 = "pri_do1";
    public static String CN_seg_do1 = "seg_do1";
    public static String CN_ed_pri1 = "ed_pri1";
    public static String CN_ed_seg1 = "ed_seg1";
    public static String CN_ext1 = "ext1";
    public static String CN_pri_do2 = "pri_do2";
    public static String CN_seg_do2 = "seg_do2";
    public static String CN_ed_pri2 = "ed_pri2";
    public static String CN_ed_seg2 = "ed_seg2";
    public static String CN_ext2 = "ext2";

    public Doespecial(String ID_MEDI2, String CN_medicamento, String CN_control, String CN_pri_do, String CN_seg_do, String CN_ed_pri, String CN_ed_seg, String CN_ext, String CN_pri_do1, String CN_seg_do1, String CN_ed_pri1, String CN_ed_seg1, String CN_ext1, String CN_pri_do2, String CN_seg_do2, String CN_ed_pri2, String CN_ed_seg2, String CN_ext2) {
        this.ID_MEDI2 = ID_MEDI2;
        this.CN_medicamento = CN_medicamento;
        this.CN_control = CN_control;
        this.CN_pri_do = CN_pri_do;
        this.CN_seg_do = CN_seg_do;
        this.CN_ed_pri = CN_ed_pri;
        this.CN_ed_seg = CN_ed_seg;
        this.CN_ext = CN_ext;
        this.CN_pri_do1 = CN_pri_do1;
        this.CN_seg_do1 = CN_seg_do1;
        this.CN_ed_pri1 = CN_ed_pri1;
        this.CN_ed_seg1 = CN_ed_seg1;
        this.CN_ext1 = CN_ext1;
        this.CN_pri_do2 = CN_pri_do2;
        this.CN_seg_do2 = CN_seg_do2;
        this.CN_ed_pri2 = CN_ed_pri2;
        this.CN_ed_seg2 = CN_ed_seg2;
        this.CN_ext2 = CN_ext2;
    }

    //<editor-fold desc="GETTER - SETTER">
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

    public String getCN_control() {
        return CN_control;
    }

    public void setCN_control(String CN_control) {
        this.CN_control = CN_control;
    }

    public String getCN_pri_do() {
        return CN_pri_do;
    }

    public void setCN_pri_do(String CN_pri_do) {
        this.CN_pri_do = CN_pri_do;
    }

    public String getCN_seg_do() {
        return CN_seg_do;
    }

    public void setCN_seg_do(String CN_seg_do) {
        this.CN_seg_do = CN_seg_do;
    }

    public String getCN_ed_pri() {
        return CN_ed_pri;
    }

    public void setCN_ed_pri(String CN_ed_pri) {
        this.CN_ed_pri = CN_ed_pri;
    }

    public String getCN_ed_seg() {
        return CN_ed_seg;
    }

    public void setCN_ed_seg(String CN_ed_seg) {
        this.CN_ed_seg = CN_ed_seg;
    }

    public String getCN_ext() {
        return CN_ext;
    }

    public void setCN_ext(String CN_ext) {
        this.CN_ext = CN_ext;
    }

    public String getCN_pri_do1() {
        return CN_pri_do1;
    }

    public void setCN_pri_do1(String CN_pri_do1) {
        this.CN_pri_do1 = CN_pri_do1;
    }

    public String getCN_seg_do1() {
        return CN_seg_do1;
    }

    public void setCN_seg_do1(String CN_seg_do1) {
        this.CN_seg_do1 = CN_seg_do1;
    }

    public String getCN_ed_pri1() {
        return CN_ed_pri1;
    }

    public void setCN_ed_pri1(String CN_ed_pri1) {
        this.CN_ed_pri1 = CN_ed_pri1;
    }

    public String getCN_ed_seg1() {
        return CN_ed_seg1;
    }

    public void setCN_ed_seg1(String CN_ed_seg1) {
        this.CN_ed_seg1 = CN_ed_seg1;
    }

    public String getCN_ext1() {
        return CN_ext1;
    }

    public void setCN_ext1(String CN_ext1) {
        this.CN_ext1 = CN_ext1;
    }

    public String getCN_pri_do2() {
        return CN_pri_do2;
    }

    public void setCN_pri_do2(String CN_pri_do2) {
        this.CN_pri_do2 = CN_pri_do2;
    }

    public String getCN_seg_do2() {
        return CN_seg_do2;
    }

    public void setCN_seg_do2(String CN_seg_do2) {
        this.CN_seg_do2 = CN_seg_do2;
    }

    public String getCN_ed_pri2() {
        return CN_ed_pri2;
    }

    public void setCN_ed_pri2(String CN_ed_pri2) {
        this.CN_ed_pri2 = CN_ed_pri2;
    }

    public String getCN_ed_seg2() {
        return CN_ed_seg2;
    }

    public void setCN_ed_seg2(String CN_ed_seg2) {
        this.CN_ed_seg2 = CN_ed_seg2;
    }

    public String getCN_ext2() {
        return CN_ext2;
    }

    public void setCN_ext2(String CN_ext2) {
        this.CN_ext2 = CN_ext2;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doespecial)) return false;

        Doespecial that = (Doespecial) o;

        if (!ID_MEDI2.equals(that.ID_MEDI2)) return false;
        if (!CN_medicamento.equals(that.CN_medicamento)) return false;
        if (CN_control != null ? !CN_control.equals(that.CN_control) : that.CN_control != null)
            return false;
        if (CN_pri_do != null ? !CN_pri_do.equals(that.CN_pri_do) : that.CN_pri_do != null)
            return false;
        if (CN_seg_do != null ? !CN_seg_do.equals(that.CN_seg_do) : that.CN_seg_do != null)
            return false;
        if (CN_ed_pri != null ? !CN_ed_pri.equals(that.CN_ed_pri) : that.CN_ed_pri != null)
            return false;
        if (CN_ed_seg != null ? !CN_ed_seg.equals(that.CN_ed_seg) : that.CN_ed_seg != null)
            return false;
        if (CN_ext != null ? !CN_ext.equals(that.CN_ext) : that.CN_ext != null) return false;
        if (CN_pri_do1 != null ? !CN_pri_do1.equals(that.CN_pri_do1) : that.CN_pri_do1 != null)
            return false;
        if (CN_seg_do1 != null ? !CN_seg_do1.equals(that.CN_seg_do1) : that.CN_seg_do1 != null)
            return false;
        if (CN_ed_pri1 != null ? !CN_ed_pri1.equals(that.CN_ed_pri1) : that.CN_ed_pri1 != null)
            return false;
        if (CN_ed_seg1 != null ? !CN_ed_seg1.equals(that.CN_ed_seg1) : that.CN_ed_seg1 != null)
            return false;
        if (CN_ext1 != null ? !CN_ext1.equals(that.CN_ext1) : that.CN_ext1 != null) return false;
        if (CN_pri_do2 != null ? !CN_pri_do2.equals(that.CN_pri_do2) : that.CN_pri_do2 != null)
            return false;
        if (CN_seg_do2 != null ? !CN_seg_do2.equals(that.CN_seg_do2) : that.CN_seg_do2 != null)
            return false;
        if (CN_ed_pri2 != null ? !CN_ed_pri2.equals(that.CN_ed_pri2) : that.CN_ed_pri2 != null)
            return false;
        if (CN_ed_seg2 != null ? !CN_ed_seg2.equals(that.CN_ed_seg2) : that.CN_ed_seg2 != null)
            return false;
        return CN_ext2 != null ? CN_ext2.equals(that.CN_ext2) : that.CN_ext2 == null;

    }

    @Override
    public int hashCode() {
        int result = ID_MEDI2.hashCode();
        result = 31 * result + CN_medicamento.hashCode();
        result = 31 * result + (CN_control != null ? CN_control.hashCode() : 0);
        result = 31 * result + (CN_pri_do != null ? CN_pri_do.hashCode() : 0);
        result = 31 * result + (CN_seg_do != null ? CN_seg_do.hashCode() : 0);
        result = 31 * result + (CN_ed_pri != null ? CN_ed_pri.hashCode() : 0);
        result = 31 * result + (CN_ed_seg != null ? CN_ed_seg.hashCode() : 0);
        result = 31 * result + (CN_ext != null ? CN_ext.hashCode() : 0);
        result = 31 * result + (CN_pri_do1 != null ? CN_pri_do1.hashCode() : 0);
        result = 31 * result + (CN_seg_do1 != null ? CN_seg_do1.hashCode() : 0);
        result = 31 * result + (CN_ed_pri1 != null ? CN_ed_pri1.hashCode() : 0);
        result = 31 * result + (CN_ed_seg1 != null ? CN_ed_seg1.hashCode() : 0);
        result = 31 * result + (CN_ext1 != null ? CN_ext1.hashCode() : 0);
        result = 31 * result + (CN_pri_do2 != null ? CN_pri_do2.hashCode() : 0);
        result = 31 * result + (CN_seg_do2 != null ? CN_seg_do2.hashCode() : 0);
        result = 31 * result + (CN_ed_pri2 != null ? CN_ed_pri2.hashCode() : 0);
        result = 31 * result + (CN_ed_seg2 != null ? CN_ed_seg2.hashCode() : 0);
        result = 31 * result + (CN_ext2 != null ? CN_ext2.hashCode() : 0);
        return result;
    }
}
