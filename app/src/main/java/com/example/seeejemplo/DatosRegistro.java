package com.example.seeejemplo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatosRegistro {
    public static final String DATOS_TABLA_MEDICAMENTOS ="medicamentos2";
    public static final String DATOS_TABLA_JARABES="jarabes";
    public static final String DATOS_TABLA_DOS_ESP="dosespecial";
    public static final String DATOS_TABLA_EDAD="peso_edad";
    public static final String DATOS_TABLA_COMER="comercial";
    public static final String STRING_TYPE="text";
    public static final String INT_TYPE="integer";
    public static final String REAL_TYPE="real";

    public static final String STRING_analgesico="analgesico";
    public static final String STRING_antialergico="antialergico";
    public static final String STRING_antibiotico="antibiotico";
    public static final String STRING_tos="tos";
    public static final String STRING_asma="asma";
    public static final String STRING_todo="todo";


    public static final String CREAR_TABLA_MEDICAMENTOS2=
            "create table "+DATOS_TABLA_MEDICAMENTOS+"(" +
                    Medicamento.ID_MEDI2+" "+INT_TYPE+" primary key autoincrement," +
                    Medicamento.CN_medicamento+" "+STRING_TYPE+" not null," +
                    Medicamento.CN_dosisAddia+" "+STRING_TYPE+" ," +
                    Medicamento.CN_DosisP+" "+STRING_TYPE+" ," +
                    Medicamento.CN_DosisP1+" "+STRING_TYPE+" ," +
                    Medicamento.CN_DosisP2+" "+STRING_TYPE+" ," +
                    Medicamento.CN_nebulizada+" "+STRING_TYPE+" ," +
                    Medicamento.CN_cada+" "+STRING_TYPE+" ," +
                    Medicamento.CN_doMaxNi+" "+STRING_TYPE+" ," +
                    Medicamento.CN_boJarabe5 +" "+STRING_TYPE+" ," +
                    Medicamento.CN_jarabe5+" "+STRING_TYPE+" ," +
                    Medicamento.CN_comprimidos+" "+STRING_TYPE+" ," +
                    Medicamento.CN_gotas+" "+STRING_TYPE+" ," +
                    Medicamento.CN_sobres+" "+STRING_TYPE+" ," +
                    Medicamento.CN_SolInhalador+" "+STRING_TYPE+" ," +
                    Medicamento.CN_contraindicaciones+" "+STRING_TYPE+" ," +
                    Medicamento.CN_precauciones+" "+STRING_TYPE+" ," +
                    Medicamento.CN_secundarios+" "+STRING_TYPE+" ," +
                    Medicamento.CN_nobrecom+" "+STRING_TYPE+" ," +
                    Medicamento.CN_indicaciones+" "+STRING_TYPE+")";

    public static final String CREAR_TABLA_JARABES=
            "create table "+DATOS_TABLA_JARABES+"("+
                    Jarabes.ID_MEDI2+" "+INT_TYPE+" primary key autoincrement," +
                    Jarabes.CN_medicamento+" "+STRING_TYPE+" not null," +
                    Jarabes.CN_primero+" "+STRING_TYPE+" ," +
                    Jarabes.CN_segundo+" "+STRING_TYPE+" ," +
                    Jarabes.CN_tercero+" "+STRING_TYPE+" ," +
                    Jarabes.CN_cuarto+" "+STRING_TYPE+" ," +
                    Jarabes.CN_quinto+" "+STRING_TYPE+" ,"+
                    Jarabes.CN_indicaciones+" "+STRING_TYPE+")";


    public static final String CREAR_TABLA_PESO=
            "create table "+DATOS_TABLA_EDAD+"("+
                    Peso_edad.ID_MEDI2+" "+INT_TYPE+" primary key autoincrement," +
                    Peso_edad.CN_edad+" "+STRING_TYPE+" not null," +
                    Peso_edad.CN_peso+" "+STRING_TYPE+" not null," +
                    Peso_edad.CN_peso1+" "+STRING_TYPE+" ," +
                    Peso_edad.CN_edad_num+" "+STRING_TYPE+")";

    public static final String CREAR_TABLA_COMER=
            "create table "+DATOS_TABLA_COMER+"("+
                    Comercial.ID_MEDI2+" "+INT_TYPE+" primary key autoincrement," +
                    Comercial.CN_medicamento+" "+STRING_TYPE+" ," +
                    Comercial.CN_nobrecom+" "+STRING_TYPE+" ," +
                    Comercial.CN_presentacion+" "+STRING_TYPE+")";

    public static final String CREAR_TABLA_DOS_ESPECIAL=
            "create table "+DATOS_TABLA_DOS_ESP+"("+
                    Doespecial.ID_MEDI2+" "+INT_TYPE+" primary key autoincrement," +
                    Doespecial.CN_medicamento+" "+STRING_TYPE+" not null," +
                    Doespecial.CN_control+" "+INT_TYPE+" not null," +
                    Doespecial.CN_pri_do+" "+REAL_TYPE+" not null," +
                    Doespecial.CN_seg_do+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_pri+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_seg+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ext+" "+STRING_TYPE+" ," +
                    Doespecial.CN_pri_do1+" "+REAL_TYPE+" ," +
                    Doespecial.CN_seg_do1+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_pri1+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_seg1+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ext1+" "+STRING_TYPE+" ," +
                    Doespecial.CN_pri_do2+" "+REAL_TYPE+" ," +
                    Doespecial.CN_seg_do2+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_pri2+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ed_seg2+" "+REAL_TYPE+" ," +
                    Doespecial.CN_ext2+" "+STRING_TYPE+")";

    private DatosReaderDbHelper openHelper;
    private SQLiteDatabase database;

    public DatosRegistro(Context context){
        //creando instancia hacia la base de datos
        openHelper= new DatosReaderDbHelper(context);
        database = openHelper.getWritableDatabase();
    }
}
