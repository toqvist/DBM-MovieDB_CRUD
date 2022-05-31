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
		
		ShowAllTables(connection);
		
		// updateActors(connection, "mikaela");

		connection.close();
	}
	
	private static void ShowAllTables(Connection connection) {
		Person myPerson = new Person(connection);
		
		ArrayList<String> document = new ArrayList<String>();
		document.add(myPerson.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);

		System.out.println(jsonDoc);

	}
	
	private static void updateActor(Connection connection, String name) {
		Person myPerson = new Person(connection);

		int antal = myPerson.updatePerson(name, "malmo_the_greatest", -1);
		System.out.println("uppdaterat : " + antal);
		
		antal = myPerson.updateActorsCity("s%", "v�xj�");
		System.out.println("uppdaterat : " + antal);		
	}
}
