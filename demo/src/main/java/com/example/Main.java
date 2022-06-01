package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.helpers.databaseHelper;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;
import com.example.objectLists.Person;
import com.example.objectLists.Movie;

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
			System.out.println("[3] - Add test person for movie CRUD -- run this before 4");
			System.out.println("[4] - Test 'movie' CRUD operations");
			

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
				case 3: //Add person for testing movie CRUD operations
					addPerson(connection, "Test", "Testsson", "2022-01-01");
					break;
				case 4: //Test CRUD for table movie
					readMovies(connection);
					addMovie(connection, "TestMovie", "1:00", "10", "G", "Test", "Testsson");
					setScore(connection, "TestMovie", 9.9f);
					readMovies(connection);
					deleteMovie(connection, "TestMovie");
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

	private static void readMovies(Connection connection) {
		Movie movie = new Movie(connection);
		System.out.println(movie.getBeansAsJSON());
	}

	private static void addMovie(Connection connection, String title, String runtime, String score, String rating, String director_fname, String director_lname) {

		Movie movie = new Movie(connection);
		movie.addMovie(title, runtime, score, rating, director_fname, director_lname);
	}

	private static void setScore (Connection connection, String title, float newScore) {
		Movie movie = new Movie(connection);
		movie.setScore(title, newScore);
	}

	private static void deleteMovie(Connection connection, String title) {
		Movie movie = new Movie(connection);
		movie.deleteMovie(title);
	}

}
