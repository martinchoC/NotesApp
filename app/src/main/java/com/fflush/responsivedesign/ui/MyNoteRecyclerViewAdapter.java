package com.fflush.responsivedesign.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fflush.responsivedesign.NewNoteDialogViewModel;
import com.fflush.responsivedesign.db.entity.NoteEntity;
import com.fflush.responsivedesign.R;

import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context context;
    private NewNoteDialogViewModel viewModel;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context context) {
        mValues = items;
        this.context = context;
        viewModel = ViewModelProviders.of((AppCompatActivity)context).get(NewNoteDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitle.setText(holder.mItem.getTitle());
        holder.textViewContent.setText(holder.mItem.getContent());

        if(holder.mItem.isFavourite()) {
            holder.imageViewFavourite.setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.imageViewFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mItem.isFavourite()) {
                    holder.mItem.setFavourite(false);
                    holder.imageViewFavourite.setImageResource(R.drawable.ic_star_border_black_24dp);
                }
                else {
                    holder.mItem.setFavourite(true);
                    holder.imageViewFavourite.setImageResource(R.drawable.ic_star_black_24dp);
                }

                //invoca al repositorio y este al objeto dao y actualiza el resultado en la base de datos
                viewModel.updateNote(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewNotes (List <NoteEntity> newNotes) {
        this.mValues = newNotes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitle;
        public final TextView textViewContent;
        public final ImageView imageViewFavourite;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewContent = view.findViewById(R.id.textViewContent);
            imageViewFavourite= view.findViewById(R.id.imageViewFavourite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitle.getText() + "'";
        }
    }
}
