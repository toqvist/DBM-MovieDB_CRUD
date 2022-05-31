package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.PersonBean;
import com.example.helpers.jsonHelper;

public class Person {
	private Connection connection;
	private ArrayList<PersonBean> persons;

	private String selectAllPersons = "SELECT * FROM person;";
	private String updateActors = "UPDATE person SET hometown = , actor_age  = ? WHERE actor_name = ?;";
	private String updateActorsShort = "UPDATE actor SET hometown = ? WHERE actor_name = ?;";
	private String updateActorCity = "UPDATE actor SET hometown = ? WHERE hometown LIKE ?;";

	public Person(Connection cn) {
		this.connection = cn;
		this.persons = new ArrayList<PersonBean>();
		getPersons();
	}

	public ArrayList<PersonBean> getPersons() {
		if (this.persons.size() > 0) {
			return this.persons;
		}

		this.persons = new ArrayList<PersonBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(selectAllPersons)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getPersons exception for statement");
			e.printStackTrace();
		}

		return this.persons;
	}

	public int updatePerson(String name, String newCity, int newAge) {

		String qry = "";
		if (newAge == -1) {
			qry = updateActorsShort;
		} else {
			qry = updateActors;
		}

		int count = -1;
		try (PreparedStatement myQry = this.connection.prepareStatement(qry)) {
			myQry.setString(1, newCity);

			if (newAge == -1) {
				myQry.setString(2, name);
			} else {
				myQry.setInt(2, newAge);
				myQry.setString(3, name);
			}

			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}

		return count;
	}

	public int updateActorsCity(String oldCity, String newCity) {

		int count = -1;
		try (PreparedStatement myQry = this.connection.prepareStatement(updateActorCity)) {
			myQry.setString(1, newCity);
			myQry.setString(2, oldCity);
			count = myQry.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateActors exception for statement");
			e.printStackTrace();
		}

		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (PersonBean personBean : this.persons) {
			beansContent += personBean.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Person", beansContent);
	}

	private PersonBean buildPerson(ResultSet resultSet) {
		PersonBean personBean = new PersonBean();

		try {
			personBean.setId(resultSet.getInt("person_id"));
			personBean.setFname(resultSet.getString("fname"));
			personBean.setLname(resultSet.getString("lname"));
			personBean.setBirthDate(resultSet.getString("birth_date"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personBean;
	}

	private void buildPersons(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.persons.add(buildPerson(resultSet));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet resultSet = query.executeQuery()) {
			buildPersons(resultSet);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

	}
}
