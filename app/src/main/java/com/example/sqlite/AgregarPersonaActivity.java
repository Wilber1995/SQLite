package com.example.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sqlite.Entidades.Personas;
import com.example.sqlite.ViewModel.PersonaViewModel;
public class AgregarPersonaActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private PersonaViewModel personaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        personaViewModel = new
                ViewModelProvider(this).get(PersonaViewModel.class);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                Personas persona = new Personas();
                persona.nombrePersona = nombre;
                persona.apellidoPersona = apellido;
                persona.edadPersona = edad;
                personaViewModel.insertPersona(persona);
                finish(); // Cierra la actividad después de guardar.
            }
        });
    }
}