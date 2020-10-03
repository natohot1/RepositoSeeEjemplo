package com.example.seeejemplo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Vector;

public class Inhalador extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private String medicamen_final;
    private String medi_introducidoFinal;
    private boolean banderaselec;

    //INICIALIZAR VARIABLES
    DatosReaderDbHelper manager;
    String[] jar_array_f = new String[5];
    DecimalFormat df = new DecimalFormat("0.0");
    DecimalFormat d = new DecimalFormat("0");
    SeekBar sb;
    Spinner spin;
    Cursor dosesp=null;
    TextView txeSubtitulo,txeTitulo, txesIndicaciones,txNComer,most,
            txeNComer,txepeso,txeCada,txeJarabe,txedosis,txeOtrosJarabes;
    String [][]array_datos=new String[20][10];
    double cada_ped_ar[];
    int controla[]=new int[1];
    //******ARRAY JARABES
    String jarabes_por5ml;
    String [] array_jarabes=new String[5];
    String[]jara_arraComercial;
    String[]jaraDosis;
    int cada_int;
    Bundle datos;
    String bandera;
    Cursor cur_bandera;
    String banString;
    ///////////////  NUEVOS VALORES
    Cursor datosMedicamento;
    String arrayJarabesAmostrar[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhalador);

        datos = getIntent().getExtras();
        medicamen_final = datos.getString("obtenido");
        // medicamen_final = datos.getString("medcinas");
        medi_introducidoFinal = datos.getString("medcinas");
        //  medi_introducidoFinal = datos.getString("obtenido");
        manager = new DatosReaderDbHelper(this);
        Context context=this;


        inicializarComponentes();
        banString=cur_bandera.getString(2);

        int spineer=spin.getSelectedItemPosition();

        //**** ABRIMOS BASE DE DATOS
        manager.openDB();
        try {
            banderaselec=manager.validarBandera();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        //*****EXTRAEMOS DATOSDE MEDICAMENTO


        try {
            array_datos=sacar_datos_en_array(medicamen_final,banString);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        /////////////*********11/01/17**********
        //SACAR JARABES SEGUN PAIS//////

        jarabes_por5ml =array_datos[10][0];
        ////// TODO PENDIENTE

        array_jarabes=manager.sacar_numStrin(jarabes_por5ml);
        String cont_sacado;
        cont_sacado=array_datos[3][0];

        asignar_seekbar(cont_sacado);

        //******SACACMOS JARABE SELECCIONADO
        //escribimos indicaciones
        String comprimidos=array_datos[11][0];
        String cada=array_datos[7][0];
        cada_ped_ar=manager.sacar_num(cada);
        cada_int= (int) cada_ped_ar[0];
        //txeCada.setText("Cada " + cada_int + "/h");


        Vector jarabes=new Vector(2);

        jarabes=jarabes_No_Comer(medicamen_final, medi_introducidoFinal, array_jarabes);
        jara_arraComercial=(String[])jarabes.elementAt(0);
        jaraDosis=(String[])jarabes.elementAt(1);
        //GENERAMOS INDICACIONES EN WEV VIEW
        indicaContraindicaciones(comprimidos);
        // txeIndicaciones.setText(comprimidos);



        titulo(medi_introducidoFinal);
        subtitulo(medicamen_final);
        mostrar_dosis();
        int varia_jarabe=jarabe_seleccioado();


        //**********VREAMS SPINER JARABES
        spin = (Spinner) findViewById(R.id.spiner_lista_jar);
        int largoJaraDosis=jaraDosis.length;

        /////***** ANIMACION DE OTROS JARABES*********************
        OTROS_JARABES_ANIMA(largoJaraDosis);


        //<editor-fold desc="CREADO SPINNER Y SACA SELECIONADO">
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,jaraDosis);

        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    update2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    update();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                txeNComer.setText("" + jara_arraComercial[position]);
                txeJarabe.setText("Jarabe "+jaraDosis[position]+"  mg/5cc");
                Toast t1 = Toast.makeText(getApplicationContext(),"Jarabes de "+jaraDosis[position]+" mg por 5 cc", Toast.LENGTH_SHORT);
                t1.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //</editor-fold>


    }
    private void OTROS_JARABES_ANIMA(int largoJaraDosis) {
        Animation desaparecer= AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        desaparecer.setFillAfter(true);
        desaparecer.setRepeatMode(Animation.REVERSE);
        desaparecer.setRepeatCount(35);
        Animation mover= AnimationUtils.loadAnimation(this,R.anim.traslacion);
        mover.setFillAfter(true);
        mover.setRepeatMode(Animation.REVERSE);
        mover.setRepeatCount(35);
        if(largoJaraDosis ==1) {
            this.txeOtrosJarabes.startAnimation(desaparecer);
            txeOtrosJarabes.setText("SOLO PRESENTACION DE: ");}
        else {
            this.txeOtrosJarabes.startAnimation(mover);
            txeOtrosJarabes.setText("OTRAS PRESENTACIONES -->  ");
        }

    }



    private void indicaContraindicaciones(String comprimidos) {
        txesIndicaciones.setText(comprimidos);

    }

    private void inicializarComponentes() {
        sb=(SeekBar)findViewById(R.id.barra);
        sb.setOnSeekBarChangeListener(this);
        spin=(Spinner)findViewById(R.id.spiner_lista_jar);
        spin.getBaseline();
        txeTitulo=(TextView)findViewById(R.id.txdTitulo);
        txeSubtitulo=(TextView)findViewById(R.id.txdSubtitulo);
        txeJarabe = (TextView) findViewById(R.id.txdJarabe);
        txepeso = (TextView) findViewById(R.id.txdpeso);
        txeOtrosJarabes = (TextView) findViewById(R.id.txdOtrasPresenta);
        txNComer = (TextView) findViewById(R.id.txdsComerciales);
        txeNComer = (TextView) findViewById(R.id.txdNombreComer);
        txesIndicaciones = (TextView) findViewById(R.id.txdContra);
        most = (TextView) findViewById(R.id.txdCantidad);
        txedosis = (TextView) findViewById(R.id.txddosis);
        txeCada = (TextView) findViewById(R.id.txdCada);

        //BUSCA BANDERA
        manager.openDB();
        try {
            cur_bandera = manager.buscarMed("BANDERA");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            datosMedicamento=manager.buscarMed(medicamen_final);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


    }

    private void titulo(String medi_introducido) {
        int largo=medi_introducido.length();
        if(largo>15){
            txeTitulo.setTextSize(18);
        }
        txeTitulo.setText(medi_introducido);

    }

    public int jarabe_seleccioado() {
        String[] jarabes_comercial = new String[5];
        int i=0,res=0;
        while (i<5) {
            jarabes_comercial[i] = array_datos[0][i + 2];
            res =jarabes_comercial[i].indexOf(medi_introducidoFinal);
            if(res!=-1){break;}
            i++;
        }
        return i;
    }

    private void mostrar_dosis() {
        String []dosi=new String[2];
        dosi[0]=array_datos[3][0];
        switch (controla[0]) {
            case 1:
                txedosis.setText("");
                break;
            case 2:
                dosi = manager.sacar_numStrin(dosi[0]);
                try {
                    txedosis.setText("Dosis " + dosi[0] + " a " + dosi[1] + " mg/kg peso");
                    sulfa(medicamen_final,dosi);
                } catch (Exception e) {
                    txedosis.setText("Dosis " + dosi[0] + " mg/kg peso");
                }
                break;
        }
    }

    private void sulfa(String sulfa, String[] dosi) {
        if(sulfa.equals("SULFAMETOXAZOL TRIMETOPRIMA")){
            txedosis.setText("40 a 60 mg/kg peso de Sulfametoxazol");
        }
    }

    private int update2()throws Exception{
        int resulInter,res_seekbar;
        resulInter=spin.getSelectedItemPosition();

        return resulInter;
    }


    private void subtitulo(String medicamen) {
        if(medicamen.equals(medi_introducidoFinal)){
            txeSubtitulo.setText("");}
        else {
            txeSubtitulo.setText(medicamen);
        }
    }

    private void update()throws Exception {
        update2();
        int resSeeb, res_espiner;
        resSeeb = sb.getProgress();


        double sekb_double;
        sekb_double = resSeeb;
        double ins_renal = 1;
        res_espiner = update2();
        String cont_sacado;
        cont_sacado=array_datos[3][0];

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


        //****DOSIS PARA DISTINGUE CLASES
        array_datos=sacar_datos_en_array(medicamen_final,banString);
        //***********SACA Y MUESTRA NOMBRES COMERCIALES

        Vector jarabes=new Vector(2);
        jarabes=jarabes_No_Comer(medicamen_final, medi_introducidoFinal, array_jarabes);
        jara_arraComercial=(String[])jarabes.elementAt(0);
        jaraDosis=(String[])jarabes.elementAt(1);

        int jar_selec=jarabe_seleccioado();



        //<editor-fold desc="MUESTRA DOSIS SEGUN PESO O EDAD">
        switch (controla[0]) {
            case 1:
                double[] dosi_esp = new double[18];
                double[] tosale_fin = new double[2];
                dosi_esp = sacar_dosis_especial();
                tosale_fin = pintar_seebar_final(dosi_esp, sekb_double);
                dosis_tos(res_espiner, tosale_fin, jaraDosis);
                break;
            case 2:
                dosis_kilo1(res_espiner, resSeeb, dosis_intDou, cada_double, jaraDosis);
                break;
        }
        //</editor-fold>
        dosis_especiales(medicamen_final,sekb_double);



    }

    private double nuevores(double resSeeb, double minimoPeso) {
        double resul;
        if(resSeeb<= minimoPeso){
            resul=0;
        }else{
            resul=resSeeb;}
        return  resul;
    }

    //*************OPERACIONES VIBRACINA//////////////////
    private String si_vibracina(String medi_vi){
        String varDoxi= "DOXICICLINA";
        /////*********************************************************************************
        if (medi_vi.equals(varDoxi)){
            varDoxi = "Con peso mayor de 43 kg";
        } else varDoxi ="";
        return varDoxi;
    }

    private Vector jarabes_No_Comer(String medicamen, String medi_introducido, String[] array_jarabes) {
        String[]nue=new String[5];
        nue[0]=array_datos[0][2];
        nue[1]=array_datos[0][3];
        nue[2]=array_datos[0][4];
        nue[3]=array_datos[0][5];
        nue[4]=array_datos[0][6];
        int cont=0,con2=0,con3=0;
        while (cont<5) {
            int valor=nue[cont].length();
            if (valor>2){
                con2=cont;
            }
            cont++;
        }
        String mios[]=new String[con2+1];
        while (con3<=(con2)){
            mios[con3]=nue[con3];
            con3++;
        }
        int largo=mios.length;
        int [][]dato_var1={{0,1,2,3,4},{1,0,2,3,4},{2,0,1,3,4},{3,0,1,2,4},{4,0,1,2,3}};


        int con1=0,variable=0;

        while(con1<largo){
            int res1=mios[con1].indexOf(medi_introducido);
            if(res1!=-1){
                variable=con1;
                break;            }
            con1++;       }
        String[] jarabes_comercial3 = new String[largo];
        String[] jaraDosis=new String[largo];

        int contaArra=0;
        while(contaArra<(largo)){
            jarabes_comercial3[contaArra]=mios[dato_var1[variable][contaArra]];
            jaraDosis[contaArra]=array_jarabes[dato_var1[variable][contaArra]];

            contaArra++;
        }
        Vector nuevo = new Vector(2);
        nuevo.add(jarabes_comercial3);
        nuevo.add(jaraDosis);
        return nuevo;
    }



    private void dosis_kilo1(int res_espiner, double resSeeb, double[] dosis_intDou, double[] cada_double, String[] array_jarabes) {
        double resultado, resultado1;
        String res;
        double minimoPeso=3; //ESTABLECE PESO MINIMO EN KILOGRAMOS
        double seekBar_minimo= nuevores(resSeeb,minimoPeso);
        double jara_int = Double.parseDouble(array_jarabes[res_espiner]);
        try {
            resultado = ((dosis_intDou[0] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            resultado1 = ((dosis_intDou[1] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            res=si_gotas2(resultado,resultado1, medicamen_final);
            most.setText(res);
        } catch (Exception e) {
            resultado = ((dosis_intDou[0] * seekBar_minimo) / (24 / cada_double[0])) / ((jara_int) / 5);
            resultado1=0;
            res=si_gotas2(resultado,resultado1, medicamen_final);
            if(resultado==0){
                most.setText("");
            }else {
                most.setText(res);
            }
        }
    }

    private String si_gotas2(double resultado, double re1, String medi) {
        boolean cont = true, gotas = false;
        String nue = "";
        int variaGotas = 20;
        double resFinalgotas = resultado, resFinalgotas2 = re1;
        String predni = "PREDNISOLONA";
        String dexgotas = "DEXTROMETORFANO";
        String paragotas = "PARACETAMOL";
        String levogotas = "LEVOCETIRIZINA";
        String ibugotas = "IBUPROFENO";
        String levodrogotas = "LEVODROPROPIZINA";

        if (resultado < 1) {
            if (medi.equals(predni)) {
                gotas = true;
                variaGotas = 40;
            }
            if (medi.equals(dexgotas)) {
                gotas = true;
                variaGotas = 27;
            }
            if (medi.equals(paragotas)) {
                gotas = true;
            }
            if (medi.equals(levogotas)) {
                gotas = true;
            }
            if (medi.equals(ibugotas)) {
                gotas = true;
            }
            if (medi.equals(levodrogotas)) {
                gotas = true;
            }
        }


        if (gotas) {
            try {
                if (resultado < 1.5) {
                    resFinalgotas = resultado * variaGotas;
                } else {
                    resFinalgotas = resultado;
                }
                if (re1 < 2) {
                    resFinalgotas2 = re1 * variaGotas;
                } else {
                    resFinalgotas2 = re1;
                }
            } catch (Exception e) {
                if (resultado < 1.5) {
                    resFinalgotas = resultado * variaGotas;
                } else {
                    resFinalgotas = resultado;
                }
            }
        }

        if (resultado == 0) {
            nue = "";
            txeCada.setText("");
        } else {
            txeCada.setText("Cada " + cada_int + "/h");
        }

        if (re1 == 0) {
            if (gotas) {
                nue = (d.format(resFinalgotas) + " gotas");
            } else {
                nue = (df.format(resFinalgotas) + " cc");
            }
        } else {
            if (gotas) {
                nue = (d.format(resFinalgotas) + " a " + d.format(resFinalgotas2) + " gotas");
            } else {
                nue = (df.format(resFinalgotas) + " a " + df.format(resFinalgotas2) + " cc");
            }
        }
        if(resultado==0){
            nue="";
        }



        return nue;
    }

    private void dosis_tos(int res_espiner, double[] dosis_intDou, String[] array_jarabes) {
        DecimalFormat df = new DecimalFormat("0.0");
        DecimalFormat d = new DecimalFormat("0");
        String si_es_gotas;
        double resultado, res=0; boolean cont=true;
        double jara_int= Double.parseDouble(array_jarabes[res_espiner]);

        resultado=dosis_intDou[0] /(jara_int/5);
        //si_es_gotas=si_gotas(resultado,res, medicamen_final);
        si_es_gotas=si_gotas2(resultado,res, medicamen_final);
        most.setText("Hasta "+ si_es_gotas);
        if(resultado==0){
            most.setTextColor(Color.RED);
            most.setTextSize(15);
            most.setText("NO INDICADO PARA LA EDAD");
        }
        if(resultado==0){
            txeCada.setText("");
        }else {
            most.setTextColor(Color.BLUE);
            most.setTextSize(27);
            String mensaje_doxi="";
            mensaje_doxi=si_vibracina(medicamen_final);
            txeCada.setText("Cada " + cada_int + "/h\n"+mensaje_doxi);
        }
    }
    private void dosis_especiales(String medica_espe, double sekb_double){
        String levodro="LEVODROPROPIZINA";
        String ketoti="KETOTIFENO";
        String aciclo="ACICLOVIR";
        String pirantel="PAMOATO DE PIRANTEL";
        String albendazol="ALBENDAZOL";

        if(medica_espe.equals(levodro)){
            if((sekb_double<12)&&(sekb_double>3)){
                most.setText("NO INDICADO");
                txeCada.setText("");
            }

        }
        if(medica_espe.equals(pirantel)){
            if((sekb_double<8)&&(sekb_double>3)){
                most.setText("NO INDICADO");
                txeCada.setText("");
            }
        }
        if(medica_espe.equals(ketoti)){
            if(sekb_double>16){
                most.setText("5 cc");
                txeCada.setText("Cada 12/h\n"+"Mayores de 3 años 2 mg dia");
            }
        }
        if(medica_espe.equals(aciclo)){
            if(sekb_double<13){
                most.setText("");
                txeCada.setText("");
            }
        }
        if(medica_espe.equals(albendazol)){
            if(sekb_double<13){
                most.setTextColor(Color.RED);
                most.setTextSize(15);
                most.setText("NO INDICADO EN MENORES DE 2 AÑOS");
                txeCada.setText("");
            }else {
                most.setTextColor(Color.BLUE);
                most.setTextSize(27);
            }
        }


    }

    public double resul_dosis1(double[] dosis, int contador, double sekbar){
        double[] edad_int=new double[10];
        int cont3=0;
        double dosis_valor1, dosis_valor2,edad_valor1,edad_valor2,edad_resul, dosis_resul,r_final=0;
        dosis_valor1= dosis[contador];
        dosis_valor2= dosis[contador+1];
        edad_valor1= dosis[contador+2];
        edad_valor2= dosis[contador+3];
        dosis_resul=(dosis_valor2-dosis_valor1)/10;
        edad_resul=(edad_valor2-edad_valor1)/10;
        for(int i=0;i<10;i++){
            edad_int[cont3]=(edad_resul*cont3)+edad_valor1;
            cont3++;}
        int cont2=0, resul1=0;
        while(cont2<10){
            if((sekbar/12)>= edad_int[cont2]&&(sekbar/12)<=edad_int[cont2+1]){
                break;
            }
            cont2++;
        }
        r_final=(dosis_resul*(cont2))+dosis_valor1;
        return r_final;
    }

    //*****VA CON dosis_final_edad  ALERGIA, TOS
    private double[] pintar_seebar_final(double[] dosis, double valor_seekb1) {
        //<editor-fold desc="SE NECEITA SEEKBARR Y VALORES DE DOSIS EDAD = ultima_dosis_edad ALERGIA, TOS">
        double []resultado=new double[1];
        double valor_seekb;
        valor_seekb=valor_seekb1/12;
        int contador=0;
        if (dosis[5] <= valor_seekb &&dosis[6] >= valor_seekb) {
            contador=3;
            resultado[0]=resul_dosis1(dosis, contador, valor_seekb1);
        }
        if (dosis[10] <= valor_seekb &&dosis[11] >= valor_seekb){
            contador=8;
            resultado[0]=resul_dosis1(dosis, contador, valor_seekb1);
        }
        if (dosis[15] <= valor_seekb &&dosis[16] >= valor_seekb){
            contador=13;
            resultado[0]=resul_dosis1(dosis, contador, valor_seekb1);
        }
        if(valor_seekb<dosis[5]){
        }
        return resultado;
        //</editor-fold>(ULTIM
    }

    private double[] sacar_dosis_especial() {
        double []resultado=new double[18];
        int cont=0;
        try {
            dosesp=manager.dos_esp(medicamen_final);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        while (cont<17){
            resultado[cont]=dosesp.getDouble(cont);
            cont++;
        }
        return resultado;
    }

    private String[][] sacar_datos_en_array(String medicamen, String bandera) throws java.sql.SQLException {
        String[][] array_inter=new String[20][9];
        Cursor c = null;
        try {
            c=manager.buscarMed(medicamen);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        c.moveToFirst();
        int m=0,j=0,d=0;

        while (m<=19){
            array_inter[m][0]=c.getString(m);
            m++;}
        c.close();
        try {
            c=manager.jar_NombreComerciales(medicamen,bandera);
        } catch (SQLException e) {
            e.printStackTrace();}
        while (j<=7){
            array_inter[0][j]=c.getString(j);
            j++;}
        c.close();
        return array_inter;

    }

    private void asignar_seekbar(String cont_sacado) {
        switch (cont_sacado){
            case "cambio":
                sb.setMax(144);
                controla[0]=1;
                int res_sekb=sb.getProgress();
                String res=edad_mese(res_sekb);
                txepeso.setText("Edad  "+res);
                break;
            default:
                sb.setMax(40);
                controla[0]=2;
                res_sekb=sb.getProgress();
                txepeso.setText("Peso  "+res_sekb+"  Kg" );
        }
    }

    private String edad_mese(double res) {
        String edad;
        if (res< 24){
            edad=String.valueOf(d.format(res))+" meses";
        }else{
            res=res/12;
            edad=String.valueOf(d.format(res))+ " años";
        }
        return edad;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try{
            update();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            update2();
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



    public void onClick(View view) {
        Intent intent = new Intent(Inhalador.this, Segundo.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (banString) {
            case "BOLIVIA":
                getMenuInflater().inflate(R.menu.menubolivia, menu);
                break;
            case "ESPAÑA":
                getMenuInflater().inflate(R.menu.menuespana, menu);
                break;
            case "PERU":
                getMenuInflater().inflate(R.menu.menuperu, menu);
                break;
            case "CHILE":
                getMenuInflater().inflate(R.menu.menuchile, menu);
                break;

        }
        return true;
    }



    public void onCli2(MenuItem item) {
        Intent intent = new Intent(Inhalador.this, BanderaActivity.class);
        startActivity(intent);

    }
}