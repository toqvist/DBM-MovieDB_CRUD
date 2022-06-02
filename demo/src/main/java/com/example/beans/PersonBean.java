package com.example.beans;

import java.util.ArrayList;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;

public class PersonBean {
    String fname;
    String lname;
    String birthDate;
    int id;

    ArrayList<ActorshipBean> actorships;

    public PersonBean() {
		this.actorships = new ArrayList<ActorshipBean>();
	}

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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;

    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public ArrayList<ActorshipBean> getActorships() {
        return actorships;
    }

    public void addActorship(ActorshipBean actorshipBean) {
        this.actorships.add(actorshipBean);
    }


    public String toJson() {
		ArrayList<keyValuePair> dataList = new ArrayList<keyValuePair>();
        
		dataList.add(new keyValuePair("fname", this.fname));
        dataList.add(new keyValuePair("lname", this.lname));
		dataList.add(new keyValuePair("birth_date", this.birthDate));
        dataList.add(new keyValuePair("id", Integer.toString(this.id)));
		
		return jsonHelper.toJsonObject(dataList);
	}
    
}
