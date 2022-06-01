package com.example.beans;

import java.util.ArrayList;

import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class GenreBean {

    ArrayList<MovieBean> movies;

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        
        dataList.add(new keyValuePair("genre_id", Integer.toString(this.id)));
		dataList.add(new keyValuePair("genre_name", this.name));

		return jsonHelper.toJsonObject(dataList);
	}
    
}
