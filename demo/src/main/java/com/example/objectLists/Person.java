package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.PersonBean;
import com.example.helpers.jsonHelper;
import com.mysql.cj.xdevapi.Result;

public class Person {
	private Connection connection;
	private ArrayList<PersonBean> persons;

	private String query_selectPerson = "SELECT * FROM person;";
	private String query_renamePerson = "UPDATE person SET fname = ?, lname  = ? WHERE (person.fname = ? AND person.lname = ?);";

	public Person(Connection cn) {
		this.connection = cn;
		this.persons = new ArrayList<PersonBean>();
		showPersons();
	}

	public ArrayList<PersonBean> showPersons() {
		if (this.persons.size() > 0) {
			return this.persons;
		}

		this.persons = new ArrayList<PersonBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectPerson)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getPersons exception for statement");
			e.printStackTrace();
		}

		return this.persons;
	}

	public void renamePerson(String oldFname, String oldLname, String newFname, String newLname ) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_renamePerson)) {
			sqlQuery.setString(1, newFname);
			sqlQuery.setString(2, newLname);
			sqlQuery.setString(3, oldFname);
			sqlQuery.setString(4, oldLname);
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("renamePerson exception");
			e.printStackTrace();
		}

		String result = "Renamed person: " + oldFname + ' ' + oldLname + " to " + newFname + ' ' + newLname;
		System.out.println(result);
	}

	public String toJson() {
		String beansContent = "";
		for (PersonBean personBean : this.persons) {
			beansContent += personBean.toJson() + ",";
		}

		return jsonHelper.toJsonArray("Person", beansContent);
	}

	private PersonBean buildPersonBean (ResultSet resultSet) {
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

	private void buildPersonBeans(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.persons.add(buildPersonBean(resultSet));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet resultSet = query.executeQuery()) {
			buildPersonBeans(resultSet);
		} catch (SQLException e) {
			System.out.println("getPerson exception for result set");
			e.printStackTrace();
		}

	}
}
