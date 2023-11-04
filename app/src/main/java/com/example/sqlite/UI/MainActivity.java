package com.example.sqlite.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sqlite.AgregarPersonaActivity;
import com.example.sqlite.MostrarListaActivity;
import com.example.sqlite.R;

public class MainActivity extends AppCompatActivity {
    private Button btnAgregarPersona;
    private Button btnMostrarLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAgregarPersona = findViewById(R.id.btnAgregarPersona);
        btnMostrarLista = findViewById(R.id.btnMostrarLista);
        btnAgregarPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this,
                            AgregarPersonaActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("AgregarPersonaActivity", "Error al guardar  persona: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        MostrarListaActivity.class);
                startActivity(intent);
            }
        });
    }
}


