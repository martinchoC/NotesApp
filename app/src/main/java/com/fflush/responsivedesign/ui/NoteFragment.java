package com.fflush.responsivedesign.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fflush.responsivedesign.NewNoteDialogFragment;
import com.fflush.responsivedesign.NewNoteDialogViewModel;
import com.fflush.responsivedesign.R;
import com.fflush.responsivedesign.db.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NoteEntity> mNoteEntityList;
    private MyNoteRecyclerViewAdapter mAdapterNotes;
    private NewNoteDialogViewModel noteViewModel;

    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    public static NoteFragment newInstance(int columnCount) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


        //indicates the fragment has a menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if(view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                //para calcular la densidad de pixeles en anchura de la pantalla
                float displayWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numColumnas = (int) (displayWidth / 180); //cada columna de la lista va a ocupar 180 pixeles por pulgada
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numColumnas, StaggeredGridLayoutManager.VERTICAL));
            }

            mNoteEntityList = new ArrayList<>();
//            mNoteEntityList.add(new NoteEntity("Lista de compras", "naranjas\nmanzanas\nmelon\nsandia",true, android.R.color.holo_blue_light));
//            mNoteEntityList.add(new NoteEntity("Estacionamiento", "Dejé el auto en la plaza, no olvidar cargar la app",false, android.R.color.holo_green_light));
//            mNoteEntityList.add(new NoteEntity("Cumple mio", "Preparar la comida y bebida.\nIr al mayorista a comprar lo necesario",true, android.R.color.holo_red_light));

            //instance the adapter and associate to recyclerview
            mAdapterNotes = new MyNoteRecyclerViewAdapter(mNoteEntityList, getActivity());
            recyclerView.setAdapter(mAdapterNotes);

            //invocarel metodo para ver si hay nuevos datos y refrescar la lista de datos
            executeViewModel();

        }
        return view;
    }

    private void executeViewModel() {
        noteViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
        noteViewModel.getAllNotes().observe(getActivity(), new Observer<List<NoteEntity>>() {
            @Override
            //updates the list notes
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {
                mAdapterNotes.setNewNotes(noteEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_note:
                showDialogNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogNewNote() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NewNoteDialogFragment dialogNewNote = new NewNoteDialogFragment();
        dialogNewNote.show(fragmentManager, "New note dialog fragment");
    }


}
