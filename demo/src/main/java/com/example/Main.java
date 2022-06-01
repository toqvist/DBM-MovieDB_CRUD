package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.helpers.databaseHelper;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;
import com.example.objectLists.Person;

public class Main {
	public static void main(String[] args) throws SQLException {
		Connection connection = databaseHelper.DbConnect("moviedb");
		
		

		// ShowAllTables(connection);
		readPersons(connection);		
		addPerson(connection, "Test", "Testsson", "2022-01-01");
		renamePerson(connection, "Test", "Testsson", "Ändrad", "Namnsson");
		readPersons(connection);
		deletePerson(connection, "Ändrad", "Namnsson");		
		readPersons(connection);
	
		connection.close();
	}
	
	private static void ShowAllTables(Connection connection) {

		ArrayList<String> outputDocument = new ArrayList<String>();
		
		Person person = new Person(connection);
		outputDocument.add(person.getBeansAsJSON()); 
		String jsonDoc = jsonHelper.toJsonObjectFromStrings(outputDocument);

		System.out.println(jsonDoc);

	}

	private static void addPerson(Connection connection, String fname, String lname, String birthDate) {
		Person person = new Person(connection);
		person.addPerson("Test", "Testsson", "2022-01-01");
	}

	private static void readPersons(Connection connection) {
		Person person = new Person(connection);
		System.out.println(person.getBeansAsJSON());
	}

	private static void renamePerson(Connection connection, String oldFname, String oldLname, String newFname, String newLname) {
		Person person = new Person(connection);
		person.renamePerson(oldFname, oldLname, newFname, newLname);
	}

	private static void deletePerson(Connection connection, String fname, String lname) {
		Person person = new Person(connection); 
		person.deletePerson(fname, lname);
	}
	
}
