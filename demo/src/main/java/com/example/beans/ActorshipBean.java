package com.example.beans;

import java.util.ArrayList;

import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class ActorshipBean {

    PersonBean actor;
    MovieBean movie;

    String role;

    public PersonBean getActor() {
        return this.actor;
    }

    public void setActor(PersonBean actor) {
        this.actor = actor;
    }

    public MovieBean getMovie() {
        return this.movie;
    }

    public void setMovie(MovieBean movie) {
        this.movie = movie;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        // fname, lname, birth_date, id
		dataList.add(new keyValuePair("fname", this.actor.fname));
        dataList.add(new keyValuePair("lname", this.actor.lname));
		dataList.add(new keyValuePair("movie_title", this.movie.title));
        dataList.add(new keyValuePair("character_role", this.role));

		
		return jsonHelper.toJsonObject(dataList);
	}
    
}
