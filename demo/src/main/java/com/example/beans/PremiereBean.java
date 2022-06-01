package com.example.beans;

import java.util.ArrayList;

import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class PremiereBean {

    String date;
    String country;

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        
		dataList.add(new keyValuePair("date", this.date));
        dataList.add(new keyValuePair("country", this.country));

		return jsonHelper.toJsonObject(dataList);
	}
}
