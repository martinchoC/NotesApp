package com.fflush.responsivedesign;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.fflush.responsivedesign.db.entity.NoteEntity;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {

    private LiveData <List <NoteEntity>> allNotes;
    private NoteRepository noteRepository;


    public NewNoteDialogViewModel (Application application) {
        super (application);

        //instance the repository
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAll();
    }

    //the fragment that needs to receive the new data list
    public LiveData <List<NoteEntity>> getAllNotes() { return allNotes; }

    //the fragment that inserts the new note must communicate it to the view model
    public void insertNote (NoteEntity newNoteEntity) { noteRepository.insert(newNoteEntity);}

    public void updateNote (NoteEntity note) { noteRepository.update(note); }
}
