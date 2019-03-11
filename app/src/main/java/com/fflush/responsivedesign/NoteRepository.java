package com.fflush.responsivedesign;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.fflush.responsivedesign.db.RoomNoteDatabase;
import com.fflush.responsivedesign.db.dao.DAONote;
import com.fflush.responsivedesign.db.entity.NoteEntity;

import java.util.List;

public class NoteRepository {

    private DAONote daoNote; //accedere para realizar las operaciones de la entidad de la db
    LiveData<List<NoteEntity>> allNotes;
    LiveData<List<NoteEntity>> allFavouriteNotes;

    public NoteRepository (Application application) { //instancia a partir de una instancia de la db
        RoomNoteDatabase db = RoomNoteDatabase.getDatabase(application);
        daoNote = db.daoNote();
        allNotes = daoNote.getAll();
        allFavouriteNotes = daoNote.getAllFavourites();
    }

    public LiveData<List<NoteEntity>> getAll() { return  allNotes; }

    public LiveData<List<NoteEntity>> getAllFavoutites() { return  allFavouriteNotes; }

    public void insert (NoteEntity note) {
        //no se puede insertar en un primer plano porque puede bloquear el thread principal de la app
        //lo hago en una clase asincronica
        new insertAsyncTask(daoNote).execute(note);

    }

    public void update (NoteEntity note) { new updateAsyncTask(daoNote).execute(note); }

    private static class insertAsyncTask extends AsyncTask <NoteEntity, Void, Void> {
        private DAONote daoNoteAsynkTask;

        insertAsyncTask (DAONote daoNote) {
            daoNoteAsynkTask = daoNote;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            daoNoteAsynkTask.insert(noteEntities[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask <NoteEntity, Void, Void> {
        private DAONote daoNoteAsynkTask;

        updateAsyncTask (DAONote daoNote) {
            daoNoteAsynkTask = daoNote;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            daoNoteAsynkTask.update(noteEntities[0]);
            return null;
        }
    }

}
