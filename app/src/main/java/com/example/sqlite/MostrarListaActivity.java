package com.example.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.Entidades.Personas;
import com.example.sqlite.ViewModel.PersonaViewModel;

import java.util.List;
public class MostrarListaActivity extends AppCompatActivity {
    private PersonaViewModel personaViewModel;
    private PersonaAdapter personaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        personaAdapter = new PersonaAdapter();
        recyclerView.setAdapter(personaAdapter);
        personaViewModel = new
                ViewModelProvider(this).get(PersonaViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, personas -> {
            personaAdapter.setPersonasList(personas);
        });
        personaAdapter.setOnItemClickListener(new PersonaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
                // Abre una nueva actividad o realiza alguna acción con la persona seleccionada
                //Intent intent = new Intent(MostrarListaActivity.this,EditarPersonaActivity.class);
                //intent.putExtra("persona", personaSeleccionada);
                //startActivityForResult(intent, 1);
                mostrarDialogoEdicion(personaSeleccionada);
            }
        });
        personaAdapter.setOnItemLongClickListener(new PersonaAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
                // Muestra una alerta de confirmación o realiza alguna acción para eliminar la persona
                //personaViewModel.deletePersona(personaSeleccionada);
                mostrarDialogoEliminacion(personaSeleccionada);
            }
        });
    }
    private void mostrarDialogoEliminacion(final Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres eliminar esta persona?")
                .setPositiveButton("Sí", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                // Eliminar la persona
                                personaViewModel.deletePersona(persona);
                            }
                        })
                .setNegativeButton("No", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }
    private void mostrarDialogoEdicion(Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres modificar esta persona?")
                .setPositiveButton("Sí", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                // Abre la actividad de edición
                                Intent intent = new
                                        Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
                                intent.putExtra("persona", persona);
                                startActivityForResult(intent, 1);
                            }
                        })
                .setNegativeButton("No", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        })
                .show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Actualizar la lista después de editar la persona
            List<Personas> listaActualizada = personaViewModel.getListaDePersonas().getValue();
            personaAdapter.setPersonasList(listaActualizada);
        }
    }
}
