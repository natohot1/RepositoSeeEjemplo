package com.example.seeejemplo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.seeejemplo.accesorio.DatosReaderDbHelper;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    TextView texto;
    private static final int duracion = 2000;
    private static final int tiempo_des = 10;
    private static final int repeticion = 4;
    private int contador = 0;
    private String botonActivo = "todo", clase = "1";
    DatosReaderDbHelper manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        manager = new DatosReaderDbHelper(this);

       // getSupportActionBar().hide();

        texto=(TextView)findViewById(R.id.texmio);
        final String textos[]={"SOLO NECESITAS","DOS O TRES LETRAS","DESLIZAR LA BARRA","YA ESTA"};
        Button salto=(Button)findViewById(R.id.btnValidar);
        manager.pasarDatos(clase,botonActivo);

        //<editor-fold desc="VALORES ANIMACION">
        final AlphaAnimation fadein = new AlphaAnimation(0.0f, 1.0f);
        fadein.setDuration(duracion);
        fadein.setStartOffset(tiempo_des);
        fadein.setFillAfter(true);

        final AlphaAnimation fadeof = new AlphaAnimation(1.0f, 0.0f);
        fadeof.setDuration(duracion);
        fadeof.setStartOffset(tiempo_des);
        fadeof.setFillAfter(true);
        //</editor-fold>

        //<editor-fold desc="METODOS DE ANIMACION">
        fadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                texto.setText(String.valueOf(textos[contador]));
                contador++;

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                texto.startAnimation(fadeof);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeof.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (contador<repeticion) {
                    texto.startAnimation(fadein);
                }
                else{
             //       manager.pasarDatos(clase,botonActivo);
                    Intent intent = new Intent(FullscreenActivity.this, Segundo.class);

                   // Bundle bundle = new Bundle();
                   // bundle.putString("boton", botonActivo);
                   // intent.putExtras(bundle);
                    startActivity(intent);}

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        texto.startAnimation(fadein);
        salto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  manager.pasarDatos(clase,botonActivo);
                Intent intent = new Intent(FullscreenActivity.this, Segundo.class);
                startActivity(intent);
            }
        });
        //</editor-fold>



    }

}
