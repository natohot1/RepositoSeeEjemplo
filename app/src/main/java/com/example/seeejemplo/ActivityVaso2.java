package com.example.seeejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.seeejemplo.accesorio.DatosReaderDbHelper;

import java.text.DecimalFormat;

public class ActivityVaso2 extends AppCompatActivity {

    TextView txtDosis, txtDosis2, txtDosis3, txtDosis4,textDilusion, texDrogaTitulo, txtVelInf, txtVelInf2, txtVelInf3, txtVelInf4,txtTitulo,txResumen,txAtributos,txPeso,txePActivo;
    NumberPicker medicamentoPicker, pickerSolvente;
    String[] array_soloNombresMedicamentos, array_dopaInicio, array_noradrenalina,array_solventes;
    String medBuscado = "DOBUTAMINA",comercialBuscado ="DOBUTAMINA HOSPIRA";
    int dosisNumero,peso, peso2, cantidadSuero,cantidadMg;
    SeekBar seekBar;
    DecimalFormat form = new DecimalFormat("0.0");
    DatosReaderDbHelper manager;
    String arrayMedicamento[] = new String[5];
    String  array_Dosis[], arrayNombresComerciales[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaso2);

        manager = new DatosReaderDbHelper(this);

        DatosReaderDbHelper dbHelper = new DatosReaderDbHelper(ActivityVaso2.this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // ARRAY DEL SOLO EL MEDICAMENTO
        arrayMedicamento = manager.medicinaComerial(comercialBuscado);
        //listado de solo nombres de medicanentos
        array_soloNombresMedicamentos = manager.nombresMedicamento();
        arrayNombresComerciales = manager.nombresComerciales();

        array_Dosis  =manager.dosis(medBuscado);
        inicilizar();
        cantidadSuero = 200;
        cantidadMg = 200;
        txPeso.setText("PESO 60 kg");
        txePActivo.setText("");
        if(medBuscado.equals(arrayMedicamento[1])){ }else {
            txePActivo.setText(arrayMedicamento[0]);
        }

        peso = 60;
        peso2 = 60;
        txAtributos.setText("Cada anpolla de "+ arrayMedicamento[1] +"\n contiene " + arrayMedicamento[2]+" mg en "+ arrayMedicamento[3]+" ml");
        txResumen.setText("Se prepara una solucion con 1 amp de "+arrayMedicamento[1]+" con "+cantidadSuero+" cc. Se pasara a la velosidad de infusion que marque en ml/hora");

        cambiodosis();
        calcularSegun();
        calculoDosis();
    }

    private void inicilizar() {
        textDilusion = findViewById(R.id.textDilusion);
        txtDosis = findViewById(R.id.textml);
        txtDosis2 = findViewById(R.id.textml2);
        txtDosis3 = findViewById(R.id.textml3);
        txtDosis4 = findViewById(R.id.textml4);
        texDrogaTitulo = findViewById(R.id.texDroga);
        txtVelInf = findViewById(R.id.texVel);
        txtVelInf2 = findViewById(R.id.texVel2);
        txtVelInf3 = findViewById(R.id.texVel3);
        txtVelInf4 = findViewById(R.id.texVel4);
        txtTitulo = findViewById(R.id.textView2);
        txAtributos = findViewById(R.id.txAmpAtributos);
        txResumen = findViewById(R.id.txdResumen);
        txPeso = findViewById(R.id.txPeso);
        txePActivo = findViewById(R.id.txPActivo);

        array_dopaInicio = getResources().getStringArray(R.array.arraydopa);

        array_solventes =getResources().getStringArray(R.array.arraysolventes);
        array_noradrenalina = getResources().getStringArray(R.array.arraynoradrenalina);
        medicamentoPicker = findViewById(R.id.numberPicker);
        medicamentoPicker.setMinValue(0);
        medicamentoPicker.setMaxValue(arrayNombresComerciales.length-1);
        medicamentoPicker.setDisplayedValues(arrayNombresComerciales);
        texDrogaTitulo.setText(String.format("%s", array_soloNombresMedicamentos[medicamentoPicker.getValue()]));
        txtVelInf.setText(String.format("%s", array_dopaInicio[0]));
        txtVelInf2.setText(String.format("%s", array_dopaInicio[1]));
        txtVelInf3.setText("");
        medicamentoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int antVal, int nuevoVal) {
                //SACAR EL NOMBRE DE LA DROGA GENERICA
                medBuscado = array_soloNombresMedicamentos[nuevoVal];
                comercialBuscado = arrayNombresComerciales[nuevoVal];
                arrayMedicamento = manager.medicinaComerial(comercialBuscado);
                array_Dosis = manager.dosis(medBuscado);
                texDrogaTitulo.setText(arrayNombresComerciales[nuevoVal]);
                if (medBuscado.equals("DOPAMINA")){
                    txtVelInf.setText(String.format("2 ug/kg/min"));
                    txtVelInf2.setText(String.format("5 ug/kg/min"));
                    txtVelInf3.setText(String.format("7 ug/kg/min"));
                }
                if (medBuscado.equals("DOBUTAMINA")){
                    txtVelInf.setText(String.format("2 ug/kg/min"));
                    txtVelInf2.setText(String.format("20 ug/kg/min"));
                    txtVelInf3.setText(String.format(""));
                }
                if (medBuscado.equals("NORADRENALINA")){
                    txtVelInf.setText(String.format("%s", array_noradrenalina[0]));
                    txtVelInf2.setText(String.format("%s", array_noradrenalina[1]));
                    txtVelInf3.setText(String.format(""));
                }
                if (medBuscado.equals("NITROGLICERINA")){
                    txtVelInf.setText(String.format("ug/min"));
                    txtVelInf2.setText(String.format(""));
                    txtVelInf3.setText(String.format(""));
                }
                if (medBuscado.equals("FENITOINA")){
                    txtVelInf.setText(String.format("17 mg/kg"));
                    txtVelInf2.setText(String.format(""));
                    txtVelInf3.setText(String.format("6 mg/kg/24 h"));
                    txtVelInf4.setText("");
                }
                cambiodosis();
                calcularSegun();
            }
        });
        pickerSolvente = findViewById(R.id.pickerSolvente);
        pickerSolvente.setMinValue(0);
        pickerSolvente.setMaxValue(5);
        pickerSolvente.setDisplayedValues(array_solventes);
        pickerSolvente.setValue(2);
        pickerSolvente.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int antVal, int nuevoVal) {
                if (nuevoVal ==0){ cantidadSuero = 50;}
                if (nuevoVal ==1){ cantidadSuero = 100; }
                if (nuevoVal ==2){ cantidadSuero = 200;}
                if (nuevoVal ==3){ cantidadSuero = 250; }
                if (nuevoVal ==4){ cantidadSuero = 500;}
                if (nuevoVal ==5){ cantidadSuero = 1000; }
                cambiodosis();
                calcularSegun();
            }
        });

        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(60);
        seekBar.setMax(200);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int pesoSek, boolean b) {
                peso = pesoSek;
                cambiodosis();
                calcularSegun();
                txPeso.setText("PESO "+pesoSek+" kg");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }



    //CALCULAR SEGUN MEDICAMENTO
    private void calcularSegun(){
        peso = seekBar.getProgress();
        String fenitoina24="";
        cantidadMg=Integer.parseInt(arrayMedicamento[2]);
        txtDosis.setText(null);
        txtDosis2.setText(null);
        txtDosis3.setText(null);
        double ampollaMicroGramos = cantidadMg*1000; //CANRIDAD AMPOLLA EN MICROGRAMOS
        double priDosis = 0;
        double segDosis = 0;
        double terDosis = 0;
        seekBar.setVisibility(View.VISIBLE);
        txPeso.setText("PESO "+peso+" kg");
        if((array_Dosis[3]).equals("ug/min")){
            //DOSIS NITROGLICERINA EMPEZAR A ESTA DOSIS Y SE PUEDE AUMENTYAR HASTA 10 VECES
            double dosisPorKilo = Double.parseDouble(array_Dosis[2]);
            priDosis = (dosisPorKilo* cantidadSuero)/ampollaMicroGramos;
            txPeso.setText("");
            seekBar.setVisibility(View.GONE);
        }
        if((array_Dosis[3]).equals("ug/kg/min")){
            double dosis = Double.parseDouble(array_Dosis[2]);
            priDosis = ((dosis*peso)*60/(ampollaMicroGramos/ cantidadSuero));
            if(array_Dosis[5]!=null) {
                if ((array_Dosis[5]).equals("ug/kg/min")) {
                    double dosis1 = Double.parseDouble(array_Dosis[4]);
                    segDosis = ((dosis1 * peso) * 60 / (ampollaMicroGramos / cantidadSuero));
                    if(array_Dosis[7]!=null) {
                        if ((array_Dosis[7]).equals("ug/kg/min")) {
                            double dosis2 = Double.parseDouble(array_Dosis[6]);
                            terDosis = ((dosis2 * peso) * 60 / (ampollaMicroGramos / cantidadSuero));
                        }
                    }
                }
            }
        }
        if((array_Dosis[3]).equals("mg/kg")){
            double ampollaMg =Double.parseDouble(arrayMedicamento[2]); //100 oh 250 mg de fentoina
            double ampollaCC =Double.parseDouble(arrayMedicamento[3]); //2 oh 5 cc de fentoina
            double dosis8 = Double.parseDouble(array_Dosis[2]);
            double dosis9 = Double.parseDouble(array_Dosis[4]); //dosis de 24 horas PARA  FENITOINA
            if(medBuscado.equals("FENITOINA")){
                //MG PO KILO DOSIS DE ATAQUE EN 20 MINUTOS
                double mgKg = (peso*dosis8);
                //cantidad de ampollas para el peso 2 o 5 cc
                priDosis = mgKg/ampollaMg; //dosisAmpollas
                //MG PO KILO DOSIS DE MANTENIMIENTO EN 24 HORAS
                double mgKg24 = (peso*dosis9);
                mgKg24 = mgKg24/ampollaMg; // NUMERO DE AMPOLLAS
                //cantidad de ampollas para el peso 2 o 5 cc
                segDosis = cantidadSuero/24; //PASAR CC HORA EN 24 HORAS
                fenitoina24=(form.format(mgKg24)+" ampollas en "+form.format(cantidadSuero)+" cc S. salina, a "+form.format(segDosis)+" ml/h");
            }
        }



        if(medBuscado.contains("FENITOINA")){
            txtDosis.setText(form.format(priDosis)+" ampollas, en "+ cantidadSuero +" cc de Salino, en 20 minutos");
            txtDosis2.setText("Dosis CARGA ESTATUS EPILEPTICO");
            txtDosis3.setText(fenitoina24);
            txtDosis4.setText("desaro---------");

        }else {
            if(priDosis >0) {
                txtDosis.setText(form.format(priDosis) + " ml/hora");
            }
            if(segDosis >0) {
                txtDosis2.setText(form.format(segDosis) + " ml/hora");
            }
            if(terDosis >0) {
                txtDosis3.setText(form.format(terDosis) + " ml/hora");
            }

        }
    }

    private void calculoDosis(){
        //calculoDosis(Integer numero, TextView miTexto ){
        peso = seekBar.getProgress();
        String[] misDosi = manager.dosis(this.medBuscado);
       // Double pesoPorDosis = peso


    }

    private void cambiodosis() {
        txAtributos.setText("Cada ampolla de "+ arrayMedicamento[1] +"\n contiene " + arrayMedicamento[2]+" mg en "+ arrayMedicamento[3]+" ml");
        txResumen.setText("Se prepara una solucion con 1 amp de "+arrayMedicamento[1]+" con "+cantidadSuero+" cc. Se pasara a la velosidad de infusion que marque en ml/hora");
        txePActivo.setText("");
        if(medBuscado.equals(arrayMedicamento[1])){ }else {
            txePActivo.setText(arrayMedicamento[0]);
        }
        if(medBuscado.contains("FENITOINA")){
            txResumen.setText("Dosis de ataque   formatera dosis");
        }
    }
}