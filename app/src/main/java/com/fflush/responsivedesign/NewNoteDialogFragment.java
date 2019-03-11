package com.fflush.responsivedesign;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.fflush.responsivedesign.db.entity.NoteEntity;

public class NewNoteDialogFragment extends DialogFragment {


    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }

    private View view;

    //Variables para las componentes del dialogFragment
    private EditText editTextTitle, editTextContent;
    private RadioGroup radioGroupColour;
    private Switch switchFavourite;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nueva nota");
        builder.setMessage("Introduzca los datos de la nueva nota")
                .setPositiveButton("Guardar nota", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String title = editTextTitle.getText().toString();
                        String content = editTextContent.getText().toString();
                        String color = "green";
                        switch (radioGroupColour.getCheckedRadioButtonId()){
                            case R.id.radioButtonRed:
                                color = "red";
                                break;
                            case R.id.radioButtonBlue:
                                color = "blue";
                                break;
                            case R.id.radioButtonGreen:
                                color = "green";
                                break;
                        }
                        boolean isFavourite =switchFavourite.isChecked();

                        //here to get an instance each time
                        //communicate the viewmodel the new data
                        NewNoteDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
                        mViewModel.insertNote(new NoteEntity(title, content, isFavourite, color));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextContent = view.findViewById(R.id.editTextContent);
        radioGroupColour = view.findViewById(R.id.radioGroupColours);
        switchFavourite = view.findViewById(R.id.switchFavourite);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
