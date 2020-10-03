package com.example.seeejemplo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class BanderaActivity extends AppCompatActivity {
    DatosReaderDbHelper manager;
    Button valido;
    Cursor cur_bandera;
    String banString,banSelecionada,botonActivo = "todo",claseRecibida,mediGenerico, mediComercial;
    Bundle datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandera);

        manager = new DatosReaderDbHelper(this);

      //  datos = getIntent().getExtras();
      //      botonActivo = datos.getString("boton");
      //      claseRecibida = datos.getString("clase");

        //SACA BANDERA Y SUS DATOS
        try {
            cur_bandera = manager.buscarMed("BANDERA");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            botonActivo = cur_bandera.getString(4);
            claseRecibida=cur_bandera.getString(3);
        } catch (Exception e) {
            e.printStackTrace();
        }


        banSelecionada="banbolivia.png";



        manager = new DatosReaderDbHelper(this);
        Context context=this;
        //BUSCA BANDERA
        manager.openDB();
        try {
            cur_bandera = manager.buscarMed("BANDERA");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        manager.close();
        banString=cur_bandera.getString(2);


        NumberPicker np = findViewById(R.id.piker);


        final TextView tv = findViewById(R.id.textView);
        final ImageView imageView = findViewById(R.id.imageBandera);
        valido=findViewById(R.id.btnValidar);
        imageView.setImageResource(R.drawable.banbolivia);

        final String[] values= {"BOLIVIA","ESPAÑA", "PERU"};
        tv.setTextColor(Color.parseColor("#FF2C834F"));
        np.setMinValue(0);
        np.setMaxValue(values.length-1);
        np.setDisplayedValues(values);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tv.setText("Selecionado : "+values[newVal]);
                if (newVal == 0){
                    imageView.setImageResource(R.drawable.banbolivia);
                    banSelecionada= "banbolivia.png";
                }
                if (newVal == 1){
                    imageView.setImageResource(R.drawable.banespana);
                    banSelecionada= "banespana.png";
                }
                if (newVal == 2){
                    imageView.setImageResource(R.drawable.banperu);
                    banSelecionada= "banperu.png";
                }


            }
        });

        valido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarbandera(banSelecionada);
                if (claseRecibida.contains("Segundo")) {
                    Intent intent = new Intent(BanderaActivity.this, Segundo.class);
                    manager.pasarDatos(claseRecibida,botonActivo);
                    startActivity(intent);
                }
                if (claseRecibida.contains("Main4")){
                    Intent intent = new Intent(BanderaActivity.this, Segundo.class);
                   // Bundle bundle = new Bundle();
                   // bundle.putString("boton",botonActivo);
                    manager.pasarDatos(claseRecibida,botonActivo);
                    startActivity(intent);
                }
                if (claseRecibida.contains("1")) {
                    Intent intent = new Intent(BanderaActivity.this, FullscreenActivity.class);
                    manager.pasarDatos("1","todo");
                    startActivity(intent);
                }
            }
        });



    }

    private void modificarbandera(String value) {
        manager.openDB();
        manager.modificarbandera(value);

    }


    public void onCli2(MenuItem item) {
    }
}
