package com.example.seeejemplo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

public class Main4Activity extends AppCompatActivity {
    TextView txTitulo,txSubtitulo, txDosis, txeCada, txGravedad,txOtras,txdOtrasTexto, txPrecausiones, txPesoEdad,txedosis, txCantidad,txJarabe,txDosiFinal;
    SeekBar seeDefi;
    private String mediGenerico, mediComercial, botonActivo, clase="Main4", medicamentoGenerico, medicamentoObtenido;
    DecimalFormat df = new DecimalFormat("0.0");
    DecimalFormat d = new DecimalFormat("0");
    private boolean banderaselec;

    Button btUno, btDos, btTres, btCuatro, btCinco;
    DatosReaderDbHelper manager;
    Bundle datos;
    Cursor cur_bandera;
    String banString;
    String arrayMedica[],arrayNombesComer[],graveAsma[],asmaArray[],arrayInhaladores[];
    int control=0,peso,resolucion;
    ImageButton btdvolver,btdBandera;
    String [][]array_datos=new String[20][10];
    String[][] array_datos2 = new String[20][10];
    double cada_ped_ar[];

    //******ARRAY JARABES
    String jarabes_por5ml;
    String [] array_jarabes=new String[5];
    String[]jara_arraComercial;
    String[]jaraDosis;
    int cada_int,position=0;
    String tipo;

    int controla[]=new int[1];
    //TAMAÑO DE LETRA BOTONES BALNCOS
    int nio = 16;
    int nio2 = 14;

    ///////////////  NUEVOS VALORES
    Cursor datosMedicamento;
    Cursor dosesp=null;
    String arrayJarabesAmostrar[];
    String cam="cambio", txtBotonMg=" mg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        restaurarCampos(savedInstanceState);
        //AVERIGUAMOS LA RESOLUCION DEL DISPOSIOTIVO
        //      resolucion = multiplica();


        datos = getIntent().getExtras();
        mediGenerico = datos.getString("medigene");
        // mediGenerico = datos.getString("medcinas");
        mediComercial = datos.getString("obtenido");
        //  mediComercial = datos.getString("obtenido");
        //  botonActivo = datos.getString("boton");

        manager = new DatosReaderDbHelper(this);
        Context context=this;

        inicilizarCompomentes();
        banString=cur_bandera.getString(2);
        manager.openDB();
        try {
            banderaselec=manager.validarBandera();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        botonActivo=cur_bandera.getString(4);

        //*****EXTRAEMOS DATOSDE MEDICAMENTO


        //   try {
        //       array_datos=manager.sacar_datos_en_array(mediGenerico,banString);
        //   } catch (java.sql.SQLException e) {
        //       e.printStackTrace();
        //   }

        //**********************TODO sacara los jarabes ordenados y por pais.
        try {
            // array_datos2=manager.sacarOrdenados_en_array(mediGenerico,mediComercial,banString);
            array_datos = manager.sacarOrdenados_en_array(mediGenerico, mediComercial, banString);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /////////////*********11/01/17**********
        //SACAR JARABES SEGUN PAIS//////
        if(banString.equals("banespana.png")){
            jarabes_por5ml =array_datos[10][0];
        }
        if(banString.equals("banbolivia.png")) {
            jarabes_por5ml = array_datos[9][0];
        }

        ////// TODO PENDIENTE

        array_jarabes=manager.sacar_numStrin(jarabes_por5ml);
        //*****************************modificado 10/05/20


        //*****************************modificado 10/05/20
        final String cont_sacado;
        cont_sacado=array_datos[3][0];

        asignar_seekbar(cont_sacado);

        //******SACACMOS JARABE SELECCIONADO
        //escribimos indicaciones
        String contraindaciones=array_datos[11][0];
        String cada=array_datos[7][0];
        cada_ped_ar=manager.sacar_num(cada);
        cada_int = (int) cada_ped_ar[0];
        //txeCada.setText("Cada " + cada_int + "/h");


        Vector jarabes=new Vector(2);

        jarabes = jarabes_No_Comer(mediGenerico, mediComercial, array_jarabes, array_datos);
        jara_arraComercial=(String[])jarabes.elementAt(0);


        //******************jara_arraComercial
        int largo_jara_arraComercial=jara_arraComercial.length;
        jaraDosis=(String[])jarabes.elementAt(1);
        txJarabe.setText("Jarabe "+jaraDosis[position]+" mg/5cc");

        txOtras.setText("" + jara_arraComercial[position]);
        if (largo_jara_arraComercial==1){
            txdOtrasTexto.setText("SOLO PRESENTACION DE");
        }

        //GENERAMOS INDICACIONES EN WEV VIEW
        indicaContraindicaciones(contraindaciones);
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        seeDefi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        titulo(mediComercial);
        subtitulo(mediGenerico);

        configurarSegmento(jaraDosis);
        //PARA MOSTAR LA DOSIS DE ENCABWEZADO
        mostrar_dosis_para();
    }

    //<editor-fold desc="CONFIGURACIONES">
    private void inicilizarCompomentes() {
        txTitulo=findViewById(R.id.txdTitulo);
        txSubtitulo = findViewById(R.id.txdSubtitulo);
        txDosis = findViewById(R.id.txdDosis);
        txeCada = findViewById(R.id.txdCada);
        txGravedad = findViewById(R.id.txdGravedad);
        txOtras = findViewById(R.id.txdOtras);
        txdOtrasTexto=findViewById(R.id.txdOtrasPresenta);
        txPrecausiones = findViewById(R.id.txdPrecausiones);
        txPesoEdad = findViewById(R.id.txdPeso);
        txDosiFinal = findViewById(R.id.txdDosisFinal);
        txCantidad = findViewById(R.id.txdCantidad);
        txJarabe=findViewById(R.id.txdJarabe);

        btUno = findViewById(R.id.btdGrave);
        btDos = findViewById(R.id.btdModerada);
        btTres = findViewById(R.id.btdLeve);
        btCuatro =findViewById(R.id.btdLeve2);
        btCinco =findViewById(R.id.btdLeve3);
        btdvolver =findViewById(R.id.btdVolver);
        btdBandera = findViewById(R.id.btBandera);
        txedosis=findViewById(R.id.txddosis);

        seeDefi=findViewById(R.id.seebDefi);


        //ESTABLECE BANDERA
        try {
            cur_bandera = manager.buscarMed("BANDERA");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        banString=cur_bandera.getString(2);
        cambioBandera(banString);



        //BOTON GRAVE SELECIONADAO


        btUno.setTextColor(Color.BLUE);
        btUno.setTextSize(nio);
        btUno.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));

        arrayMedica=arrayMedicamento(mediGenerico);

        //CONFIGURA ALTO DE ALGUNOS TEXTVIEW
        //  configuraAlto();

    }
    //<editor-fold desc="FUNCIONES BOTONES PARA OTRAS PRESENTACIONES">
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void configurarbtdGrave() throws java.sql.SQLException {
        btUno.setTextColor(Color.BLUE);
        btUno.setTextSize(nio);
        btUno.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));

        btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btDos.setTextSize(nio2);
        btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));

        btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btTres.setTextSize(nio2);

        btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCuatro.setTextSize(nio2);

        btCinco.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCinco.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCinco.setTextSize(nio2);
        position=0;
        txOtras.setText("" + jara_arraComercial[position]);
        txJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
        control=0;
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
    private void configurarbtdModerada() throws java.sql.SQLException {
        btDos.setTextColor(Color.BLUE);
        btDos.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        btDos.setTextSize(nio);

        btUno.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btUno.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btUno.setTextSize(nio2);

        btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btTres.setTextSize(nio2);

        btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCuatro.setTextSize(nio2);

        btCinco.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCinco.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCinco.setTextSize(nio2);
        position=1;
        txOtras.setText("" + jara_arraComercial[position]);
        txJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
        control=1;
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    private void configurarbtdLeve() throws java.sql.SQLException {
        btTres.setTextColor(Color.BLUE);
        btTres.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        btTres.setTextSize(nio);

        btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCuatro.setTextSize(nio2);

        btCinco.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCinco.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCinco.setTextSize(nio2);

        btUno.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btUno.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btUno.setTextSize(nio2);
        btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btDos.setTextSize(nio2);
        //  txGravedad.setText(graveAsma[2]);
        position=2;
        txOtras.setText("" + jara_arraComercial[position]);
        txJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
        // txeJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");

        control=2;
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    private void configurarbtdLeve2() throws java.sql.SQLException {
        btCuatro.setTextColor(Color.BLUE);
        btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        btCuatro.setTextSize(nio);

        btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btTres.setTextSize(nio2);

        btCinco.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCinco.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCinco.setTextSize(nio2);

        btUno.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btUno.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btUno.setTextSize(nio2);
        btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btDos.setTextSize(nio2);
        position=3;
        txOtras.setText("" + jara_arraComercial[position]);
        txJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
        // txeJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");

        control=2;
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
    private void configurarbtdLeve3() throws java.sql.SQLException {
        btCinco.setTextColor(Color.BLUE);
        btCinco.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        btCinco.setTextSize(nio);

        btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btTres.setTextSize(nio2);
        btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btCuatro.setTextSize(nio2);

        btUno.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btUno.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btUno.setTextSize(nio2);
        btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
        btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
        btDos.setTextSize(nio2);
        position=4;
        txOtras.setText("" + jara_arraComercial[position]);
        txJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
        control=2;
        try {
            update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
    private void configurarSegmento(String[] arrayInhaladores) {

        //CONFIGURA OTRAS PRESENTACIONES
        int largo=arrayInhaladores.length;
        if(largo==1){
            btTres.setVisibility(View.GONE);
            btDos.setVisibility(View.GONE);
            btCuatro.setVisibility(View.GONE);
            btCinco.setVisibility(View.GONE);
            btUno.setText(arrayInhaladores[0]+txtBotonMg);
            btCinco.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        }
        if(largo==2){
            btTres.setVisibility(View.GONE);
            btCuatro.setVisibility(View.GONE);
            btCinco.setVisibility(View.GONE);
            btUno.setText(arrayInhaladores[0]+txtBotonMg);
            btDos.setText(arrayInhaladores[1]+txtBotonMg);
            btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btDos.setTextSize(nio2);
        }
        if (largo==3) {
            btCuatro.setVisibility(View.GONE);
            btCinco.setVisibility(View.GONE);
            btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btDos.setTextSize(nio2);
            btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btTres.setTextSize(nio2);
            btUno.setText(arrayInhaladores[0]+txtBotonMg);
            btDos.setText(arrayInhaladores[1]+txtBotonMg);
            btTres.setText(arrayInhaladores[2]+txtBotonMg);
        }
        if (largo==4) {
            btCinco.setVisibility(View.GONE);
            btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btDos.setTextSize(nio2);
            btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btTres.setTextSize(nio2);
            btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btCuatro.setTextSize(nio2);
            btCuatro.setText(arrayInhaladores[3]+txtBotonMg);
            btUno.setText(arrayInhaladores[0]+txtBotonMg);
            btDos.setText(arrayInhaladores[1]+txtBotonMg);
            btTres.setText(arrayInhaladores[2]+txtBotonMg);
        }
        if (largo==5) {
            btDos.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btDos.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btDos.setTextSize(nio2);
            btTres.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btTres.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btTres.setTextSize(nio2);
            btCuatro.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btCuatro.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btCuatro.setTextSize(nio2);
            btCinco.setBackground(this.getResources().getDrawable(R.drawable.animadeselcionado));
            btCinco.setTextColor(getResources().getColor(R.color.colorLetBlancDif));
            btCinco.setTextSize(nio2);
            btCinco.setText(arrayInhaladores[4]+txtBotonMg);
            btCuatro.setText(arrayInhaladores[3]+txtBotonMg);
            btUno.setText(arrayInhaladores[0]+txtBotonMg);
            btDos.setText(arrayInhaladores[1]+txtBotonMg);
            btTres.setText(arrayInhaladores[2]+txtBotonMg);
        }
    }
    //</editor-fold>

    private void asignar_seekbar (String cont_sacado){
        //String cam="cambio";
        int res_sekb = seeDefi.getProgress();
        if (cont_sacado.equals(cam)){
            seeDefi.setMax(144);
            controla[0] = 1;
            String res=edad_mese(res_sekb);
            //  txPesoEdad.setText("Edad  "+res);
            txPesoEdad.setText("Edad");
            txDosiFinal.setText("DOSIS PARA " +res);

        }else {
            seeDefi.setMax(40);
            //seeDefi.setMin(3);
            controla[0]=2;
            res_sekb=seeDefi.getProgress();
            txPesoEdad.setText("Peso");
            txDosiFinal.setText("DOSIS PARA " +res_sekb+ " Kg");

            if (res_sekb<4){
                    txeCada.setText("");
                    txCantidad.setText("");
                    txDosiFinal.setText("AVANCE LA BARRA");
            }else {
                txeCada.setText("Cada " + cada_int + "/h");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("PESO_INTRODUCIDO",peso);
        outState.putInt("POSITION",position);
        outState.putString("MED_FINAL", mediComercial);
    }
    private void restaurarCampos(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            this.peso= savedInstanceState.getInt(String.valueOf(peso));
            this.mediComercial = savedInstanceState.getString(mediComercial);

        }
    }
    private void configuraAlto(){
        int heightNu; //your textview height
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        if (height > 1500){
            heightNu = 70;
            txCantidad.getLayoutParams().height =heightNu;
            txeCada.getLayoutParams().height =heightNu;
            txDosiFinal.getLayoutParams().height =heightNu;
            txTitulo.getLayoutParams().height = heightNu+20;
        }
        if (height > 1290){
            heightNu = 50;
            txCantidad.getLayoutParams().height =heightNu;
            txeCada.getLayoutParams().height =heightNu;
            txDosiFinal.getLayoutParams().height =heightNu;
            txTitulo.getLayoutParams().height = heightNu+20;
        }
        if (height < 900){
            //heightNu = 70;
            //txCantidad.getLayoutParams().height =heightNu;
            //txeCada.getLayoutParams().height =heightNu;
            //txDosiFinal.getLayoutParams().height =heightNu;
            //txTitulo.getLayoutParams().height = heightNu+20;

        }


    }
    //</editor-fold>

    //region FUNCIONES DE EXTRACCION DE DATOS
    private String[] arrayMedicamento(String medicina){
        Cursor medi=null;
        try {
            medi= manager.buscarMed(medicina);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        medi.moveToFirst();
        int largo=medi.getColumnCount();
        String devul[]=new String[largo];
        int cont=0;
        while (cont<largo){
            devul[cont]=medi.getString(cont);
            cont++;
        }
        medi.close();
        manager.close();
        return devul;
    }
    public int jarabe_seleccioado () {
        String[] jarabes_comercial = new String[5];
        int i = 0, res = 0;
        while (i < 5) {
            jarabes_comercial[i] = array_datos[0][i + 2];
            res = jarabes_comercial[i].indexOf(mediComercial);
            if (res != -1) {
                break;
            }
            i++;
        }
        return i;
    }
    private void mostrar_dosis_para() {//MUETRA DOSIS DE ENCABEZADO
        String[] dosi = new String[2];
        dosi[0] = array_datos[3][0];
        switch (controla[0]) {
            case 1:
                txedosis.setText("");
                break;
            case 2:
                dosi = manager.sacar_numStrin(dosi[0]);
                try {
                    txedosis.setText("Dosis " + dosi[0] + " a " + dosi[1] + " mg/kg peso");
                    sulfa(mediGenerico, dosi);
                } catch (Exception e) {
                    txedosis.setText("Dosis " + dosi[0] + " mg/kg peso");
                }
                break;
        }
    }
    private void update() throws java.sql.SQLException{
        int resSeeb, res_espiner;
        resSeeb = seeDefi.getProgress();
        double sekb_double = resSeeb;
        // res_espiner = position;
        String cont_sacado = array_datos[3][0];
        asignar_seekbar(cont_sacado);
        //****NUEVAS OPERACONES JARABES
        jarabes_por5ml =array_datos[10][0];

        //*******OPERACIONES PARA DOSIS
        String dosis_int, cada_inter;
        int contador = 0;
        double[] dosis_intDou, cada_double;
        dosis_int = (array_datos[3][0]);
        dosis_intDou = manager.sacar_num(dosis_int);
        cada_inter = array_datos[7][0];
        cada_double = manager.sacar_num(cada_inter);

        //****DOSIS PARA DISTINGUE CLASES+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //***********SACA Y MUESTRA NOMBRES COMERCIALES

        Vector jarabes=new Vector(2);
        jarabes = jarabes_No_Comer(mediGenerico, mediComercial, array_jarabes, array_datos);
        jara_arraComercial=(String[])jarabes.elementAt(0);
        jaraDosis=(String[])jarabes.elementAt(1);

        int jar_selec=jarabe_seleccioado();
        //<editor-fold desc="MUESTRA DOSIS SEGUN PESO O EDAD">
        switch (controla[0]) {
            case 1:
                //DOSIS SEGUN EDAD
                double[] dosi_esp = new double[18];
                double[] tosale_fin = new double[2];
                dosi_esp = manager.sacar_dosis_especial(mediGenerico);
                tosale_fin = manager.dosis_final_edad(dosi_esp, sekb_double);
                dosis_tos(position, tosale_fin, jaraDosis, mediGenerico);
                break;
            case 2:
                //DOSIS SEGUN PESO
                String valorAmostrar= manager.dosis_kilo1(position, resSeeb, dosis_intDou, cada_double, jaraDosis, mediGenerico,cada_int);
                txCantidad.setText(valorAmostrar);
                break;
        }
        //</editor-fold>
        dosis_especiales(mediGenerico,sekb_double);
    }
    public void dosis_tos (int res_espiner, double[] dosis_intDou, String[] array_jarabes, String med_final){
        String si_es_gotas;
        double resultado, res = 0;
        //   boolean cont = true;
        double jara_int = Double.parseDouble(array_jarabes[res_espiner]);

        resultado = dosis_intDou[0] / (jara_int / 5);
        //  si_es_gotas = si_gotas2(resultado, res, mediGenerico);
        si_es_gotas = manager.si_gotas2(resultado, res, med_final,cada_int);

          if (resultado == 0) {
              txeCada.setText("");
          } else {
              txeCada.setText("Cada " + cada_int + "/h");
          }

        txCantidad.setText("Hasta " + si_es_gotas);
        if (resultado == 0) {
            txCantidad.setTextColor(Color.RED);
            txCantidad.setText("NO INDICADO PARA LA EDAD");
        }else{
           // txCantidad.setTextColor(Color.BLUE);
            txCantidad.setTextColor(getResources().getColor(R.color.colorLetMorada));
        }

        if (med_final.contains("DOXICICLINA")){
            if(resultado == 0){
                txeCada.setText("");
            }else {
                String mensaje_doxi = "";
                mensaje_doxi = si_vibracina(med_final);
                txeCada.setText("Cada " + cada_int + "/h\n" + mensaje_doxi);
            }
        }
    }


    public Vector jarabes_No_Comer2(String medi_introducido, String jarabes, String[][] array_datos) {
        // SACAMOS LOS DISTINTOS ARRAY SEGUN LAS  CONCENTRACIONES EXISTENTES A ORDENAR
        String[] nue = new String[5];
        nue[0] = array_datos[0][2];
        nue[1] = array_datos[0][3];
        nue[2] = array_datos[0][4];
        nue[3] = array_datos[0][5];
        nue[4] = array_datos[0][6];
        String[] arrJarabe5 = jarabes.split("-");
        int largo = arrJarabe5.length, contador = 0;


        String[] jarabes_comercial3 = new String[largo];

        while (contador < largo) {
            jarabes_comercial3[contador] = nue[contador];
            contador++;
        }

        Vector nuevo = new Vector(2);
        nuevo.add(jarabes_comercial3);
        nuevo.add(jarabes);
        return nuevo;

    }

    public Vector jarabes_No_Comer (String medicamen, String medi_introducido, String[] array_jarabes, String[][] array_datos){
        // SACAMOS LOS DISTINTOS ARRAY SEGUN LAS  CONCENTRACIONES EXISTENTES A ORDENAR
        String[] nue = new String[5];
        nue[0] = array_datos[0][2];
        nue[1] = array_datos[0][3];
        nue[2] = array_datos[0][4];
        nue[3] = array_datos[0][5];
        nue[4] = array_datos[0][6];
        int cont = 0, con2 = 0, con3 = 0;
        while (cont < 5) {
            int valor = nue[cont].length();
            if (valor > 2) {
                con2 = cont;
            }
            cont++;
        }
        String mios[] = new String[con2 + 1];
        while (con3 <= (con2)) {
            mios[con3] = nue[con3];
            con3++;
        }
        int largo = mios.length;
        int[][] dato_var1 = {{0, 1, 2, 3, 4}, {1, 0, 2, 3, 4}, {2, 0, 1, 3, 4}, {3, 0, 1, 2, 4}, {4, 0, 1, 2, 3}};


        int con1 = 0, variable = 0;

        //devuelve el numero de la tabla donde esta el medicameto buscado  "variable"
        while (con1 < largo) {
           int res1 = mios[con1].indexOf(medi_introducido);
           if (res1 != -1) {
               variable = con1;
               break;
           }
            con1++;
        }
        String[] jarabes_comercial3 = new String[largo];
        String[] jaraDosis = new String[largo];

        int contaArra = 0;
        while (contaArra < (largo)) {
            jarabes_comercial3[contaArra] = mios[dato_var1[variable][contaArra]];
            jaraDosis[contaArra] = array_jarabes[dato_var1[variable][contaArra]];
            contaArra++;
        }
        Vector nuevo = new Vector(2);
        nuevo.add(jarabes_comercial3);
        nuevo.add(jaraDosis);
        return nuevo;

    }

    private void dosis_especiales (String medica_espe,double sekb_double){
        String levodro = "LEVODROPROPIZINA";
        String ketoti = "KETOTIFENO";
        String aciclo = "ACICLOVIR";
        String pirantel = "PAMOATO DE PIRANTEL";
        String albendazol = "ALBENDAZOL";
        String hidroxicina = "HIDROXIZINA";
        if (medica_espe.equals(levodro)) {
            if ((sekb_double < 12) && (sekb_double > 3)) {
                txCantidad.setTextColor(Color.RED);
                txCantidad.setText("NO INDICADO MENOS DE 11 KG");
                txeCada.setText("");
            } else {
                txCantidad.setTextColor(getResources().getColor(R.color.colorLetMorada));
            }
        }
        if (medica_espe.equals(pirantel)) {
            if ((sekb_double < 8) && (sekb_double > 3)) {
                txCantidad.setTextColor(Color.RED);
                txCantidad.setText("NO INDICADO MENOS DE 7 KG");
                txeCada.setText("");
            } else {
                txCantidad.setTextColor(getResources().getColor(R.color.colorLetMorada));
            }
        }
        if (medica_espe.equals(ketoti)) {
            if (sekb_double > 16) {
                txCantidad.setText("5 cc");
                txeCada.setText("Cada 12/h\n" + "Mayores de 3 años 2 mg dia");
            }
        }
        if (medica_espe.equals(aciclo)) {
            if (sekb_double < 13) {
                txCantidad.setText("");
                txeCada.setText("");
            }
        }
        if (medica_espe.equals(albendazol)) {
            if (sekb_double < 13) {
                txCantidad.setTextColor(Color.RED);
                txCantidad.setText("NO INDICADO EN MENORES DE 2 AÑOS");
                txeCada.setText("");
            } else {
                txCantidad.setTextColor(getResources().getColor(R.color.colorLetMorada));
            }
        }

        if (medica_espe.equals(hidroxicina)) {
            if (sekb_double < 10) {
                txCantidad.setTextColor(Color.RED);
                txCantidad.setText("NO INDICADO EN MENORES DE 1 AÑOS");
                txeCada.setText("");
            } else {
                txCantidad.setTextColor(getResources().getColor(R.color.colorLetMorada));
                txeCada.setText("Cada 6/h");
            }
        }
    }
    private String edad_mese ( double res){
        String edad;
        if (res < 24) {
            edad = String.valueOf(d.format(res)) + " MESES";
        } else {
            res = res / 12;
            edad = String.valueOf(d.format(res)) + " AÑOS";
        }
        return edad;
    }
    private int multiplica(){
        int valor = 0;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        if (width < 768){
            valor = 7;
        }
        return valor;
    }
    private void titulo(String medi_introducido) {
        int largo=medi_introducido.length();
        if(largo>15){
            //  txTitulo.setTextSize(18);
        }
        txTitulo.setText(medi_introducido);

    }
    private void subtitulo(String medicamen) {
        if(medicamen.equals(mediComercial)){
            txSubtitulo.setText("");}
        else {
            txSubtitulo.setText(medicamen);
        }
    }
    private void indicaContraindicaciones (String comprimidos){
        txPrecausiones.setText(comprimidos);
    }

    //METODOS ONCLICK
    public void onClickBandera(View view) {
        Intent intent = new Intent(Main4Activity.this, BanderaActivity.class);
        manager.pasarDatos(clase,botonActivo);
        startActivity(intent);
    }
    public void onClickVolver(View view) {
        Intent intent = new Intent(Main4Activity.this, Segundo.class);
        Bundle bundle = new Bundle();
        bundle.putString("boton", botonActivo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClickOtras(View view) throws java.sql.SQLException {
        switch (view.getId()){
            //BOTONES GRAVE A LEVE CONFIGURAR
            case R.id.btdGrave:
                configurarbtdGrave();
                break;
            case R.id.btdModerada:
                configurarbtdModerada();
                break;
            case R.id.btdLeve:
                configurarbtdLeve();
                break;
            case R.id.btdLeve2:
                configurarbtdLeve2();
                break;
            case R.id.btdLeve3:
                configurarbtdLeve3();
                break;

        }
    }

    //*************OPERACIONES VIBRACINA//////////////////
    private String si_vibracina (String medi_vi){
        String varDoxi = "DOXICICLINA";
        /////*********************************************************************************
        if (medi_vi.equals(varDoxi)) {
            varDoxi = "Con peso mayor de 43 kg";
        } else varDoxi = "";
        return varDoxi;
    }
    private void sulfa (String sulfa, String[]dosi){
        if (sulfa.equals("SULFAMETOXAZOL TRIMETOPRIMA")) {
            txedosis.setText("40 a 60 mg/kg peso de Sulfametoxazol");
        }
    }

    //CAMBIO ICONO BANDERA
    private void cambioBandera(String bandera){
        if (bandera.contains("bolivia")){
            btdBandera.setBackground(this.getResources().getDrawable(R.drawable.banbolivia));}
        if (bandera.contains("espana")){
            btdBandera.setBackground(this.getResources().getDrawable(R.drawable.banespana));}
        if (bandera.contains("peru")){
            btdBandera.setBackground(this.getResources().getDrawable(R.drawable.banperu));}
        if (bandera.contains("chile")){
            btdBandera.setBackground(this.getResources().getDrawable(R.drawable.banchile));}
    }

}
