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

		int selection = 0;
		boolean selectionLoop = true;



		while (selectionLoop) {

			System.out.println("----------------------------------------------------");
			System.out.println("WELCOME TO MOVIEDB");
			System.out.println("----------------------------------------------------");
			System.out.println("Each test will create, update and delete a row, showing the changes before and after.");
            System.out.println("[1] - Exit Application");
			System.out.println("[2] - Test 'person' CRUD operations");
			System.out.println("[3] - Test 'movie' CRUD operations");
			System.out.println("[4] - Test 'genre' CRUD operations");

			switch (selection) {
				case 0: // Do nothing
					break;
				case 1: // Exit application
					selectionLoop = false;
					break;
				case 2: // Test CRUD for table person
					readPersons(connection);
					// Add test person and rename them
					addPerson(connection, "Test", "Testsson", "2022-01-01");
					renamePerson(connection, "Test", "Testsson", "Ändrad", "Namnsson");
					readPersons(connection);
					// Delete person and show that it is gone
					deletePerson(connection, "Ändrad", "Namnsson");
					readPersons(connection);
					break;
				case 3: //Test CRUD for table movie
					readMovies();

				break;
				case 4: // Test CRUD for table genre
					// readGenres(connection);
					// // Add test genre
					// addGenre(connection, "Testgenre");
					// readGenres(connection);
					// // Rename test genre
					// renameGenre(connection, "Testgenre", "Ändrad");
					// readGenres(connection);
					// // Delete test genre
					// deleteGenre(connection, "Ändrad");
					// readGenres(connection);
					break;
			}


		}

		// ShowAllTables(connection);

		connection.close();
	}

	private static void ShowAllTables(Connection connection) {

		ArrayList<String> outputDocument = new ArrayList<String>();

		Person person = new Person(connection);
		outputDocument.add(person.getBeansAsJSON());
		String jsonDoc = jsonHelper.toJsonObjectFromStrings(outputDocument);

		System.out.println(jsonDoc);

	}


	private static void readPersons(Connection connection) {
		Person person = new Person(connection);
		System.out.println(person.getBeansAsJSON());
	}

	private static void addPerson(Connection connection, String fname, String lname, String birthDate) {
		Person person = new Person(connection);
		person.addPerson("Test", "Testsson", "2022-01-01");
	}

	private static void renamePerson(Connection connection, String oldFname, String oldLname, String newFname,
			String newLname) {
		Person person = new Person(connection);
		person.renamePerson(oldFname, oldLname, newFname, newLname);
	}

	private static void deletePerson(Connection connection, String fname, String lname) {
		Person person = new Person(connection);
		person.deletePerson(fname, lname);
	}

}
