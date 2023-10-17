package com.jarabe.seeejemplo.accesorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jarabe.seeejemplo.FullscreenActivity;
import com.jarabe.seeejemplo.R;

public class AcercaActivity extends AppCompatActivity {
    TextView txtSup, txtMed, txtInf;
    Button btnAcerVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        inciciarcomponentes();
        txtSup.setText("Luisa Maria\n" +
                "Ya tendremos los dias que nos falto disfrutar\n" +
                "\n"+
                "Daniela\n" +
                "Valeria\n" +
                "Carmen\n" +
                "Ferran\n" +
                "\n"+
                "La inspiracion de mis dias\n" +
                        "Sin sus sonrisas no hubiera llegado\n" +
                        "Perdon por los dias de ausencia");
        txtMed.setText("");
        txtInf.setText("Felix Alvarez Tellez\n" +
                "Medico - UMSA 1993\n" +
                "Catalunya-España-2023");

        btnAcerVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcercaActivity.this, FullscreenActivity.class);
                startActivity(intent);
            }
        });





    }

    private void inciciarcomponentes() {
        txtSup=(TextView)findViewById(R.id.txAcersup);
        txtMed=(TextView) findViewById(R.id.txAcerMed);
        txtInf=(TextView) findViewById(R.id.txAcerInf);
        btnAcerVolver=(Button) findViewById(R.id.btAcerVolver);
    }
}