package edu.hanu.cinematicket.model;

public class Movie {
    private String title;
    private String description;
    private int thumbnail;

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    private int coverPhoto;

    public Movie(String title,  int thumbnail, int coverPhoto, String description) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.description = description;
    }

    public Movie(String title, int thumbnail, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
    }

    public Movie(String title, String description, int thumbnail, String studio, String rating, String streamingLinking) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }


    public Movie(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }


}