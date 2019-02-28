package com.fflush.responsivedesign.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
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
            mNoteEntityList.add(new NoteEntity("Lista de compras", "naranjas\nmanzanas\nmelon\nsandia",true, android.R.color.holo_blue_light));
            mNoteEntityList.add(new NoteEntity("Estacionamiento", "Dejé el auto en la plaza, no olvidar cargar la app",false, android.R.color.holo_green_light));
            mNoteEntityList.add(new NoteEntity("Cumple mio", "Preparar la comida y bebida.\nIr al mayorista a comprar lo necesario",true, android.R.color.holo_red_light));

            mAdapterNotes = new MyNoteRecyclerViewAdapter(mNoteEntityList, getActivity());
            recyclerView.setAdapter(mAdapterNotes);
        }
        return view;
    }


}
