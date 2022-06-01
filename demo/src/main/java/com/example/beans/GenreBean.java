package com.example.beans;

import java.util.ArrayList;

import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class GenreBean {

    ArrayList<MovieBean> movies;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MovieBean> getMovies() {
        return movies;
    }

    public void addMovie (MovieBean movieBean) {
        this.movies.add(movieBean);
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        
		dataList.add(new keyValuePair("genre_name", this.name));

		return jsonHelper.toJsonObject(dataList);
	}
    
}
