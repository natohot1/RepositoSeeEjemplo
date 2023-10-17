package com.jarabe.seeejemplo.accesorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jarabe.seeejemplo.FullscreenActivity;
import com.jarabe.seeejemplo.R;

public class ColaboraActivity extends AppCompatActivity {
    TextView txtencaCol, txtAgra, txtFel;
    Button btnConti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colabora);
        txtAgra=(TextView)findViewById(R.id.txencaAgra);
        txtencaCol=(TextView) findViewById(R.id.txencaColaboara);
        txtFel=(TextView) findViewById(R.id.txencaFelix);
        btnConti=(Button)findViewById(R.id.btConti);
        btnConti.setOnClickListener(v -> {
            Intent intent = new Intent(ColaboraActivity.this, FullscreenActivity.class);
            startActivity(intent);
        });

        txtAgra.setText("Mi agradecimiento a:\n" +
                "Fidel Alvarez\n" +
                "hermano tu apoyo es fundamental.");
        txtencaCol.setText("La vida del medico es un constante aprender\n" +
                "Cada paciente un nuevo detalle de la enfermedad\n" +
                "A los colegas quen tienen que medicar a niños\n " +
                "(mis amigos pediatras lo haran\n" +
                "con mas elegancia)\n" +
                "espero les sea de ayuda");
        txtFel.setText("Marcela\n" +
                "Su aporte me fue de gran ayuda");



    }
}