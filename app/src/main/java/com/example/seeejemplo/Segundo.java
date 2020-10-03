package com.example.seeejemplo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Segundo extends AppCompatActivity {
    AutoCompleteTextView auto;
    String botonActivo ="todo", clase = "segundo";
    DatosReaderDbHelper manager;
    Button btAntibiotico, btAnalgesicos, btTos,btAlergia,btAsma,btTodo;
    ImageButton btBanera;

    Cursor cur_bandera;
    String banString;
    String mediGenerico;
    String[] medicaAu= {"uno","dos"};
    String[] medicaLi= {"uno","dos"};
    ArrayAdapter<String> adapterLi;
    ArrayAdapter<String> adapterAu;
    ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundo);

        manager = new DatosReaderDbHelper(this);

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        Context context=this;

        cargarcomponentes();

        banString=cur_bandera.getString(2);
        if (banString.equals("banespana.png")) {
            medicaAu = getResources().getStringArray(R.array.medica_array);
            medicaLi = getResources().getStringArray(R.array.medica_array);
        }
        if (banString.equals("banbolivia.png")) {
            medicaAu = getResources().getStringArray(R.array.medica_arrayBolivia);
            medicaLi = getResources().getStringArray(R.array.medica_arrayBolivia);
        }

        //SACAMOS DAOTOS BOTON Y CLASE
        botonActivo=cur_bandera.getString(4);

        //SE ESTABLECE BOTON ACTIVO
       if (botonActivo.contains("todo")){
           activarTodo();
       }
       if (botonActivo.contains("asma")){
           activarAsma();
       }
       if (botonActivo.contains("tos")){
           activarTos();
       }
       if (botonActivo.contains("antibiotico")){
           activarAntibiotico();
       }
       if (botonActivo.contains("antialergico")){
           activarAlergia();
       }
       if (botonActivo.contains("analgesico")){
           activarAnalgesico();
       }



        //********BORRAR
     //   final String nombre = getIntent().getStringExtra("nomb");
        final int nombre1=6;



        //******BORRAR

       // actualizarBotones(btTodo);


        adapterAu = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,medicaAu);
        adapterLi = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,medicaLi);

        lista.setAdapter(adapterLi);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String medicinaSeleccionada=medicaLi[position];
               String generico = manager.mediComer(medicinaSeleccionada);
               String clase = "jarabe";
               if (generico.contains("NEBULIZA") || generico.contains("INHALA") ){
                   clase = "asma";
               }
               pasarJarabe2(generico,clase,medicinaSeleccionada);
            }
        });
        auto.setThreshold(1);
        auto.setAdapter(adapterAu);
        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Intent intent = new Intent(Segundo.this, Main4Activity.class);
                String clase = "jarabe";
                String obtenido = auto.getText().toString();
                mediGenerico = manager.mediComer(obtenido);
                if (mediGenerico.contains("NEBULIZA") || mediGenerico.contains("INHALA") ){
                    clase = "asma";
                }
                pasarJarabe2(mediGenerico,clase,obtenido);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitarBotones();
            }
        });


        ImageButton bt = (ImageButton) findViewById(R.id.bor);
        bt.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auto.setText("");
                ponerBotones();
                closeKeyboard();
            }
        });

        //FOCO EN AUTOCOMPLETE
        quitar();

    }

    public void quitar(){
        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    quitarBotones();
                }else {
                    closeKeyboard();
                }
            }
        });

    }



    private void pasarJarabe2(String mediGen, String clase, String mediSelec) {
        //PASA MEDIANTE BOTONES DISTINGUE ASMA Y ACTIVITY4 MEDIANTE VARIABLE CLASE
        Intent intent = new Intent(Segundo.this, Main4Activity.class);
        if (clase.equals("asma")) {
            intent = new Intent(Segundo.this, Asma.class);
        }
        Bundle bundle = new Bundle();
        bundle.putString("boton", botonActivo);
        bundle.putString("medigene", mediGen);
        bundle.putString("obtenido", mediSelec);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void cargarcomponentes() {
        auto = findViewById(R.id.autoCompleteTextView);
        btAnalgesicos= findViewById(R.id.btdAnalgesicos);
        btAntibiotico = findViewById(R.id.btdAntibiticos);
        btTos = findViewById(R.id.btdTos);
        btAlergia = findViewById(R.id.btdAntialergicos);
        btAsma = findViewById(R.id.btdAsma);
        btTodo = findViewById(R.id.btdTodo);
        lista = findViewById(R.id.miLIsta);
        lista.requestFocusFromTouch();
        btBanera = findViewById(R.id.btBandera);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels

        int anchoP=0;
        int ancho=horizotal(width,height);

        if(ancho<1000){
            anchoP=11;
        }
        if(ancho>1000 && ancho<2000){
            anchoP=14;
        }
        if(ancho>2000){
            anchoP=20;
        }

        btAntibiotico.setTextSize(anchoP);
        btAnalgesicos.setTextSize(anchoP);
        btTos.setTextSize(anchoP);
        btAlergia.setTextSize(anchoP);
        btAsma.setTextSize(anchoP);
        btTodo.setTextSize(anchoP);

        //ESTABLECE BANDERA
        try {
            cur_bandera = manager.buscarMed("BANDERA");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        banString=cur_bandera.getString(2);
        cambioBandera(banString);

    }

    private int horizotal(int width, int height) {
        int devulto=width;
        if(height<width){
            devulto=width/2;}
        return devulto;
    }

    //METODOS ONCLICK
    public void onClickAtras(View view) {
    }
    public void onClick1(View view) {
        switch (view.getId()){
            case (R.id.btdAnalgesicos):
                botonActivo = DatosRegistro.STRING_analgesico;
                activarAnalgesico();
                closeKeyboard();
                break;
            case (R.id.btdAntialergicos):
                botonActivo = DatosRegistro.STRING_antialergico;
                activarAlergia();
                closeKeyboard();
                break;
            case (R.id.btdAntibiticos):
                botonActivo = DatosRegistro.STRING_antibiotico;
                activarAntibiotico();
                closeKeyboard();
                break;
            case (R.id.btdTos):
                botonActivo = DatosRegistro.STRING_tos;
                activarTos();
                closeKeyboard();
                break;
            case (R.id.btdAsma):
                botonActivo = DatosRegistro.STRING_asma;
                activarAsma();
                closeKeyboard();
                break;
            case (R.id.btdTodo):
                botonActivo = DatosRegistro.STRING_todo;
                activarTodo();
                closeKeyboard();
                break;
        }
    }
    public void onClickBandera(View view) {
        Intent intent = new Intent(Segundo.this, BanderaActivity.class);
        startActivity(intent);
    }

    //QUITAR TECLADO
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    //<editor-fold desc="CONFIGURACIOON DE BOTONES">
    //CONFIGURACION BOTONES
    private void activarTodo() {
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.medica_array);
            medicaAu = getResources().getStringArray(R.array.medica_array); }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.medica_arrayBolivia);
            medicaAu = getResources().getStringArray(R.array.medica_arrayBolivia);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //  btTodo.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btTodo.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btTodo.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
        // btUno.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void activarAsma() {
       // botonActivo = "asma";
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.ASMA);
            medicaAu = getResources().getStringArray(R.array.ASMA);
        }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.ASMA);
            medicaAu = getResources().getStringArray(R.array.ASMA);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //   btAsma.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btAsma.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btAsma.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void activarTos() {
       // botonActivo ="tos";
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTITUSIGENOS);
            medicaAu = getResources().getStringArray(R.array.ANTITUSIGENOS);
        }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTITUSIGENOS_BOLIVIA);
            medicaAu = getResources().getStringArray(R.array.ANTITUSIGENOS_BOLIVIA);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //     btTos.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btTos.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btTos.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void activarAntibiotico() {
      //  botonActivo ="antibiotico";
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTIBIOTICOS);
            medicaAu = getResources().getStringArray(R.array.ANTIBIOTICOS);
        }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTIBIOTICOS_BOLIVIA);
            medicaAu = getResources().getStringArray(R.array.ANTIBIOTICOS_BOLIVIA);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //      btAntibiotico.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btAntibiotico.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btAntibiotico.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void activarAlergia() {
     //   botonActivo ="antialergico";
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTIALERGICOS);
            medicaAu = getResources().getStringArray(R.array.ANTIALERGICOS);
        }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.ANTIALERGICOS_BOLIVIA);
            medicaAu = getResources().getStringArray(R.array.ANTIALERGICOS_BOLIVIA);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //      btAlergia.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btAlergia.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btAlergia.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void activarAnalgesico() {
      //  botonActivo ="analgesico";
        actualizarBotones3();
        if (banString.equals("banespana.png")) {
            medicaLi = getResources().getStringArray(R.array.ANALGESICOS);
            medicaAu = getResources().getStringArray(R.array.ANALGESICOS);
        }
        if (banString.equals("banbolivia.png")) {
            medicaLi = getResources().getStringArray(R.array.ANALGESICOS_BOLIVIA);
            medicaAu = getResources().getStringArray(R.array.ANALGESICOS_BOLIVIA);}
        adapterLi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaLi);
        adapterAu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicaAu);
        auto.setAdapter(adapterAu);
        lista.setAdapter(adapterLi);
        adapterLi.notifyDataSetChanged();
        adapterAu.notifyDataSetChanged();
        //     btAnalgesicos.setBackgroundColor(getResources().getColor(R.color.botnActivo));
        btAnalgesicos.setTextColor(getResources().getColor(R.color.colorAzul));
        manager.pasarDatos("Segundo",botonActivo);
        btAnalgesicos.setBackground(this.getResources().getDrawable(R.drawable.animapresionado));
    }
    private void actualizarBotones3(){
        //  btTodo.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btTodo.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btTodo.setBackgroundResource(R.drawable.animadeselcionado);
        //  btAnalgesicos.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btAnalgesicos.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btAnalgesicos.setBackgroundResource(R.drawable.animadeselcionado);
        //    btAlergia.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btAlergia.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btAlergia.setBackgroundResource(R.drawable.animadeselcionado);
        //    btAsma.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btAsma.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btAsma.setBackgroundResource(R.drawable.animadeselcionado);
        //    btTos.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btTos.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btTos.setBackgroundResource(R.drawable.animadeselcionado);
        //     btAntibiotico.setBackgroundColor(getResources().getColor(R.color.botnInactivo));
        btAntibiotico.setTextColor(getResources().getColor(R.color.colorLetBlaneutra));
        btAntibiotico.setBackgroundResource(R.drawable.animadeselcionado);
    }
    public void quitarBotones() {
        btAnalgesicos.setVisibility(View.GONE);
        btAlergia.setVisibility(View.GONE);
        btAnalgesicos.setVisibility(View.GONE);
        btTos.setVisibility(View.GONE);
        btTodo.setVisibility(View.GONE);
        btAntibiotico.setVisibility(View.GONE);
        btAsma.setVisibility(View.GONE);
    }
    public void ponerBotones(){
        btAnalgesicos.setVisibility(View.VISIBLE);
        btAlergia.setVisibility(View.VISIBLE);
        btAnalgesicos.setVisibility(View.VISIBLE);
        btTos.setVisibility(View.VISIBLE);
        btTodo.setVisibility(View.VISIBLE);
        btAntibiotico.setVisibility(View.VISIBLE);
        btAsma.setVisibility(View.VISIBLE);

    }
    //</editor-fold>

    //CAMBIO ICONO BANDERA
    private void cambioBandera(String bandera){
        if (bandera.contains("bolivia")){
            btBanera.setBackground(this.getResources().getDrawable(R.drawable.banbolivia));}
        if (bandera.contains("espana")){
            btBanera.setBackground(this.getResources().getDrawable(R.drawable.banespana));}
        if (bandera.contains("peru")){
            btBanera.setBackground(this.getResources().getDrawable(R.drawable.banperu));}
        if (bandera.contains("chile")){
            btBanera.setBackground(this.getResources().getDrawable(R.drawable.banchile));}
    }


}















