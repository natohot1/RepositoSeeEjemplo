package com.example.seeejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;

public class IndiceAsma4 extends AppCompatActivity {
    Bundle datos;
    private String medicamen_final,nombreGenerico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indice_asma4);

        datos = getIntent().getExtras();
        medicamen_final = datos.getString("medcinas");

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.asma_gravedad2);
    }

    public void onClickAtras(View view) {
        Intent intent = new Intent(IndiceAsma4.this, Asma.class);
        Bundle bundle = new Bundle();
        bundle.putString("medigene", medicamen_final);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
