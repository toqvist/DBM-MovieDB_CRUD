package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.beans.GenreshipBean;
import com.example.helpers.databaseHelper;
import com.example.helpers.jsonHelper;
import com.example.helpers.keyValuePair;
import com.example.objectLists.Person;
import com.example.objectLists.Movie;
import com.example.objectLists.Actorship;
import com.example.objectLists.Genre;

public class Main {
	public static void main(String[] args) throws SQLException {
		Connection connection = databaseHelper.DbConnect("moviedb");

		boolean selectionLoop = true;

		while (selectionLoop) {

			System.out.println("----------------------------------------------------");
			System.out.println("WELCOME TO MOVIEDB!");
			System.out.println("----------------------------------------------------");
			System.out.println("Each test will create, update and delete a row, showing the changes before and after.");
            System.out.println("[1] - Exit Application");
			System.out.println("[2] - Test 'person' CRUD operations");
			System.out.println("[3] - Add test person for movie CRUD -- run this before 4");
			System.out.println("[4] - Test 'movie' CRUD operations");
			System.out.println("[5] - Delete test person");
			System.out.println("[6] - Test genre CRUD operations");
			System.out.println("[7] - Add test movie --Remember to create test person first!");
			System.out.println("[8] - Add movie to person as actor.");
			
			Scanner scanner = new Scanner(System.in);
            int userInput = 0;

            try {
                    
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input must be a valid integer from printed choices!");
            } 

			switch (userInput) {
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
					addMovie(connection, "TestMovie", 120, 10.0f, "E", "Test", "Testsson");
					setScore(connection, "TestMovie", 9.9f);
					readMovies(connection);
					deleteMovie(connection, "TestMovie");
				break;
				case 5: //Delete test person
					deletePerson(connection, "Test", "Testsson");
					break;
				case 6: //Test CRUD for table genre
					readGenres(connection);
					addGenre(connection, "TestGenre");
					renameGenre(connection, "TestGenre2", "TestGenre");
					readGenres(connection);
					deleteGenre(connection, "TestGenre2");
					readGenres(connection);
					break;
				case 7: //Create test movie
				addMovie(connection, "TestMovie", 120, 10.0f, "E", "Test", "Testsson");
					break;
				case 8: //Add movie to person as actor
					addActorship(connection, "Test", "Testsson", "TestMovie", "Super Test Man");
					readActorships(connection);
					changeRole(connection, "Evil Test Man", "Super Test Man");
					readActorships(connection);
					deleteActorship(connection, "Test", "Testsson", "TestMovie");
					readActorships(connection);
					break;
				case 9: //Add genre to movie
					addGenreToMovie("TestGenre", "TestMovie");
					removeGenreFromMovie("TestGenre", "TestMovie");
					readMovieGenres("TestMovie");
					break;

			}

			//Wait for user input, so that the console does not become crowded with menu choices.
            userInput = 0;
			System.out.println("[ Press any key to continue ]");
            try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	private static void addMovie(Connection connection, String title, int runtime, float score, String rating, String director_fname, String director_lname) {

		Movie movie = new Movie(connection);
		movie.addMovie(title, runtime, score, rating, director_fname, director_lname);
	}

	private static void setScore (Connection connection, String title, float newScore) {
		Movie movie = new Movie(connection);
		movie.setScore(newScore, title);
	}

	private static void deleteMovie(Connection connection, String title) {
		Movie movie = new Movie(connection);
		movie.deleteMovie(title);
	}

	//Generate CRUD methods for genre table
	private static void readGenres(Connection connection) {
		Genre genreObj = new Genre(connection);
		System.out.println(genreObj.getBeansAsJSON());
	}

	private static void addGenre(Connection connection, String genre) {
		Genre genreObj = new Genre(connection);
		genreObj.addGenre(genre);
	}

	private static void renameGenre (Connection connection, String newGenre, String oldGenre) {
		Genre genreObj = new Genre(connection);
		genreObj.renameGenre(newGenre, oldGenre);
	}

	private static void deleteGenre(Connection connection, String genre) {
		Genre genreObj = new Genre(connection);
		genreObj.deleteGenre(genre);
	}
	
	private static void addActorship(Connection connection, String fname, String lname, String title, String role) {
		Person person = new Person(connection);
		Movie movie = new Movie(connection);
		Actorship actorship = new Actorship(connection, movie, person);
		actorship.addActorship(fname, lname, title, role);
	}

	private static void readActorships (Connection connection) {
		Person person = new Person(connection);
		Movie movie = new Movie(connection);
		Actorship actorship = new Actorship(connection, movie, person);
		System.out.println(actorship.getBeansAsJSON());
	}

	private static void changeRole (Connection connection, String newRole, String oldRole) {
		Person person = new Person(connection);
		Movie movie = new Movie(connection);
		Actorship actorship = new Actorship(connection, movie, person);
		actorship.changeRole(newRole, oldRole);
	}

	private static void deleteActorship (Connection connection, String fname, String lname, String title) {
		Person person = new Person(connection);
		Movie movie = new Movie(connection);
		Actorship actorship = new Actorship(connection, movie, person);
		actorship.deleteActorship(fname, lname, title);
	}

	public void readMovieGenres(Connection connection, String title) {
		Movie movie = new Movie(connection);
		Genre genre = new Genre(connection);
		Genreship genreship = new Genreship(connection, genre, movie);


		System.out.println(movie.getBeansAsJSON());
	}


}
