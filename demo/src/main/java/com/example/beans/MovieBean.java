package com.example.beans;

import java.util.ArrayList;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class MovieBean {
    String title;
    int runtime;
    float score;
    String rating;
    int directorId;
    int id;

    ArrayList<ActorshipBean> actors;
    ArrayList<PremiereBean> premieres;
    ArrayList<GenreBean> genres;

    public MovieBean () {
        this.actors = new ArrayList<ActorshipBean>();
        this.premieres = new ArrayList<PremiereBean>();
        this.genres = new ArrayList<GenreBean>();
	}

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPremiereBean (PremiereBean premiereBean) {
        this.premieres.add(premiereBean);
    }

    public ArrayList<PremiereBean> getPremiereBeans () {
        return this.premieres;
    }

    public void addGenreBean (GenreBean genreBean) {
        this.genres.add(genreBean);
    }

    public ArrayList<GenreBean> getGenreBeans () {
        return this.genres;
    }

    public void addActorBean (ActorshipBean actorship) {
        this.actors.add(actorship);
    }

    public ArrayList<ActorshipBean> getActorBeans () {
        return this.actors;
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();

        dataList.add(new keyValuePair("movie_id", Integer.toString(this.directorId)));
		dataList.add(new keyValuePair("title", this.title));
        dataList.add(new keyValuePair("runtime", Integer.toString(this.runtime)));
		dataList.add(new keyValuePair("score", Float.toString(this.score)));
        dataList.add(new keyValuePair("rating", this.rating));
        dataList.add(new keyValuePair("director_id", Integer.toString(this.directorId)));

		return jsonHelper.toJsonObject(dataList);
	}
}