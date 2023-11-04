package com.example.sqlite.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Personas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idPersona;
    public String nombrePersona;
    public String apellidoPersona;
    public int edadPersona;
    @NonNull
    @Override
    public String toString() {
        return nombrePersona + " " + apellidoPersona + " " + edadPersona;
    }
}