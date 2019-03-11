package com.fflush.responsivedesign.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "notas")
public class NoteEntity {

    @PrimaryKey (autoGenerate = true)
    public int id;

    public String title;
    public String content;
    public boolean favourite;
    public String color;

    public NoteEntity(String title, String content, boolean favourite, String color) {
        this.title = title;
        this.content = content;
        this.favourite = favourite;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
