package com.example.sqlite.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sqlite.Entidades.Personas;
import java.util.List;
@Dao
public interface PersonaDAO {
    @Insert
    void Insert(Personas personaEntity);
    @Delete
    void deletePersona(Personas persona);
    @Update
    void updatePersona(Personas persona);
    @Query("SELECT * FROM PERSONAS")
    LiveData<List<Personas>> obtenerTodasLasPersonas();
}