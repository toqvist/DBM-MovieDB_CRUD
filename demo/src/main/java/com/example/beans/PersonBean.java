package com.example.beans;

import java.util.ArrayList;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class PersonBean {
    String fname;
    String lname;
    String birthDate;
    int id;

    ArrayList<MovieBean> actorships;

    public String getFname() {
        return fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBirth_date() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDate() {
        return this.birthDate;
    }


    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        // fname, lname, birth_date, id
		dataList.add(new keyValuePair("fname", this.fname));
        dataList.add(new keyValuePair("lname", this.lname));
		dataList.add(new keyValuePair("birth_date", this.birthDate));
        dataList.add(new keyValuePair("id", Integer.toString(this.id)));
		
		return jsonHelper.toJsonObject(dataList);
	}
    
}
