package com.example.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.sqlite.Entidades.Personas;
import com.example.sqlite.ViewModel.PersonaViewModel;
public class EditarPersonaActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private PersonaViewModel personaViewModel;
    private Personas personaActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        personaViewModel = new
                ViewModelProvider(this).get(PersonaViewModel.class);
        // Obtener la persona actual de la actividad anterior
        personaActual = (Personas)
                getIntent().getSerializableExtra("persona");
        // Verificar si la persona actual es nula
        if (personaActual != null) {
            etNombre.setText(personaActual.nombrePersona);
            etApellido.setText(personaActual.apellidoPersona);
            etEdad.setText(String.valueOf(personaActual.edadPersona));
        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                if (personaActual != null) {
                    // Actualizar la información de la persona actual
                    personaActual.nombrePersona = nombre;
                    personaActual.apellidoPersona = apellido;
                    personaActual.edadPersona = edad;
                    // Actualizar en la base de datos
                    personaViewModel.updatePersona(personaActual);
                    finish(); // Cierra la actividad después de guardar.
                }
            }
        });
    }
}