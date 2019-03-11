package com.fflush.responsivedesign.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fflush.responsivedesign.db.dao.DAONote;
import com.fflush.responsivedesign.db.entity.NoteEntity;


@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
public abstract class RoomNoteDatabase extends RoomDatabase {

    public abstract DAONote daoNote();  //permite obtener un objeto DAO cuando lo necesite
    private static volatile RoomNoteDatabase INSTANCE; //variable para guardar la instancia de la DB


    //metodo para obtener la instancia de la DB
    public static RoomNoteDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            //todavia no se instancio la variable
            //generamos la instancia
            synchronized (RoomNoteDatabase.class){
                //volvemos a comprobar que el valor de instance es null y asi lo instanciamos con la libreria room
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomNoteDatabase.class,
                            "notes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
