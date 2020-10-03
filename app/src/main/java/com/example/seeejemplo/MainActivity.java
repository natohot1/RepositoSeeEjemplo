package com.example.seeejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import java.sql.SQLException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int duracion = 1000;
    private static final int tiempo_des = 10;
    private static final int repeticion = 4;
    private int contador = 0;
    DatosReaderDbHelper manager;
    String textos[] = new String[5];

    String var_paraceta = "PARACETAMOL";
    String var_amoxic = "AMOXICILINA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DatosReaderDbHelper(this);
        manager.openDB();

        try {
            valoresIniciales();
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

    private void valoresIniciales() throws SQLException {
        ins_fecha_Actual_amoxi();
        Cursor p = manager.buscarMed(var_paraceta);
        Cursor a = manager.buscarMed(var_amoxic);
        String esPara=p.getString(6);
        String esAmoxi=a.getString(6);
        int fechas[]=new int[2];
        fechas=numeros_de_fechas(esAmoxi,esPara);

        /*if(fechas[1]>fechas[0]){
            insParacetamol_posteior();
            pasarAnimacion();
        }


        if(fechas[1]==0){
            insParacetamol_posteior();
            pasarAnimacion();
        }else {
            pasarA2();
        }*/


        pasarAnimacion();




    }

    private void pasarAnimacion() {
        Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
        startActivity(intent);
    }




    //////////////////////
    public void insParacetamol_posteior() throws SQLException {
        //  INSERTAR FECHA POSTERIOR EN PARACETAMOL


        Date fecha = new Date();
        Date masfecha = new Date();
        int masdias = 1;
        masfecha = (Date) manager.sumarRestarDiasFecha(fecha, masdias);
        ContentValues registro1 = new ContentValues();
        registro1.put(Medicamento.CN_nebulizada, String.valueOf(masfecha));
        registro1.put(Medicamento.CN_DosisP2, "1");
        manager.myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS, registro1, Medicamento.CN_medicamento + "=?", new String[]{var_paraceta});
    }

    public void ins_fecha_Actual_amoxi() {
        //INSERTA FECHA ACTUAL EN AMOXICILINA
        Date fecha = new Date();
        ContentValues registro = new ContentValues();
        registro.put(Medicamento.CN_nebulizada, String.valueOf(fecha));
        manager.myDataBase.update(DatosRegistro.DATOS_TABLA_MEDICAMENTOS, registro, Medicamento.CN_medicamento + "=?", new String[]{var_amoxic});

    }




    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }



    private int[] numeros_de_fechas(String antes, String despues){
        int[] devuelve=new int[2];
        if(despues.equals("0")){
            devuelve[1]=0;
            String mes1=antes.substring(4,7);
            String dia1=antes.substring(8,10);
            int largo=antes.length();
            String ano1=antes.substring(largo-1);

            String mesPrimero=mesSacado(mes1);
            StringBuilder sb =new StringBuilder();
            String fechaFin=sb.append(ano1).append(mesPrimero).append(dia1).toString();
            int fe1=Integer.parseInt(fechaFin);
            devuelve[0]=fe1;
        }else {
            String mes1 = antes.substring(4, 7);
            String dia1 = antes.substring(8, 10);
            int largo=antes.length();
            String ano1=antes.substring(largo-1);

            String mes2 = despues.substring(4, 7);
            String dia2 = despues.substring(8, 10);
            int largo1=antes.length();
            String ano2=antes.substring(largo1-1);

            String mesPrimero = mesSacado(mes1);
            String mesSegundo = mesSacado(mes2);

            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            String fechaFin = sb.append(ano1).append(mesPrimero).append(dia1).toString();
            String fechaFin2 = sb1.append(ano2).append(mesSegundo).append(dia2).toString();
            int fe1 = Integer.parseInt(fechaFin);
            int fe2 = Integer.parseInt(fechaFin2);

            devuelve[0] = fe1;
            devuelve[1] = fe2;
        }


        return devuelve;
    }



    String mesSacado(String mes1){
        String messacado="";
        if(mes1.equals("Jan"))
        { messacado="1";}
        if(mes1.equals("Feb"))
        { messacado="2";}
        if(mes1.equals("Mar"))
        { messacado="3";}
        if(mes1.equals("Apr"))
        { messacado="4";}
        if(mes1.equals("May"))
        { messacado="5";}
        if(mes1.equals("Jun"))
        { messacado="6";}
        if(mes1.equals("Jul"))
        { messacado="7";}
        if(mes1.equals("Aug"))
        { messacado="8";}
        if(mes1.equals("Sep"))
        { messacado="9";}
        if(mes1.equals("Oct"))
        { messacado="10";}
        if(mes1.equals("Nov"))
        { messacado="11";}
        if(mes1.equals("Dec"))
        { messacado="12";}
        return messacado;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK)
        {

        }

        return true;
    }




}
