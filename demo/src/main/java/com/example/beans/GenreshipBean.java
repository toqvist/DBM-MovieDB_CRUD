package com.example.beans;

import java.util.ArrayList;

import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class GenreshipBean {

    GenreBean genre;
    MovieBean movie;

    public GenreBean getGenre() {
        return genre;
    }

    public void setGenre(GenreBean genre) {
        this.genre = genre;
    }

    public MovieBean getMovie() {
        return movie;
    }

    public void setMovie(MovieBean movie) {
        this.movie = movie;
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        	
		dataList.add(new keyValuePair("movie_title", this.movie.getTitle()));
        dataList.add(new keyValuePair("genre_name", this.genre.getName()));

		
		return jsonHelper.toJsonObject(dataList);
	}
    
}
