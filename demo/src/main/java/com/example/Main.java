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
		
		Person person = new Person(connection);

		// ShowAllTables(connection);
		
		// person.addPerson("Test", "Testsson", "2022-01-01");
		// person.renamePerson("Test", "Testsson", "Ändrad", "Namnsson");
		//System.out.println(person.getBeanAsJSON());
		// person.deletePerson("Ändrad", "Namnsson");
		// person.showPersons();

		System.out.println(person.getBeanAsJSON());
		connection.close();
	}
	
	private static void ShowAllTables(Connection connection) {

		ArrayList<String> outputDocument = new ArrayList<String>();
		
		Person person = new Person(connection); //This
		outputDocument.add(person.getBeanAsJSON()); //Only these two lines are unique to each table
		String jsonDoc = jsonHelper.toJsonObjectFromStrings(outputDocument);

		System.out.println(jsonDoc);

	}
	
}
