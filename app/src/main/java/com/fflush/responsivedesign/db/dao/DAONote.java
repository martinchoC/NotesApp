package com.fflush.responsivedesign.db.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.fflush.responsivedesign.db.entity.NoteEntity;

import java.util.List;

@Dao
public interface DAONote {

    @Insert
    void insert(NoteEntity note);

    @Update
    void update(NoteEntity note);

    @Query("DELETE FROM notas")
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :idNote")
    void deleteById(int idNote);

    @Query("SELECT * FROM notas ORDER BY title ASC")
    LiveData<List<NoteEntity>> getAll(); //Livedata permite trabajar con datos en tiempo real

    @Query("SELECT * FROM notas WHERE favourite LIKE 1") //1 es igual a 'true' y 0 es 'false'
    LiveData<List<NoteEntity>> getAllFavourites();
}
