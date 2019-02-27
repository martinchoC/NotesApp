package com.fflush.responsivedesign;

public class Note {

    private String title;
    private String content;
    private boolean favourite;
    private int color;

    public Note(String title, String content, boolean favourite, int color) {
        this.title = title;
        this.content = content;
        this.favourite = favourite;
        this.color = color;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
