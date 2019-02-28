package com.fflush.responsivedesign.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fflush.responsivedesign.db.entity.NoteEntity;
import com.fflush.responsivedesign.R;

import java.util.List;

public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<NoteEntity> mValues;
    private Context context;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context context) {
        mValues = items;
        this.context = context;
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

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
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
