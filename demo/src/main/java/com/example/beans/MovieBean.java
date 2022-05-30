package com.example.beans;

public class MovieBean {
    String title;
    int runtime;
    float score;
    String rating;
    int directorId;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public float getScore() {
        return this.score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getDirectorId() {
        return this.directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }
}