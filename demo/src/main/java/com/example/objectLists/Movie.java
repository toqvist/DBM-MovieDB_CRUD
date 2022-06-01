package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.MovieBean;
import com.example.helpers.jsonHelper;

public class Movie {
	private Connection connection;
	private ArrayList<MovieBean> movies;
	
    private String query_createMovie = "CALL sp_add_movie(?,?,?,?,?);";
    private String query_selectMovie = "SELECT * FROM movie;";
    private String query_updateScore = "UPDATE movie SET score = ? WHERE movie.title = ?;";
    private String query_deleteMovie = "DELETE FROM movie WHERE movie.title = ?;";
 


	public Movie(Connection connection) {
		this.connection = connection;
		this.movies = new ArrayList<MovieBean>();
		getmovies();
	}

	public void addMovie(String title, String runtime, String score, String rating, String director_fname, String director_lname) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_createMovie)) {
			sqlQuery.setString(1, title);
			sqlQuery.setString(2, runtime);
			sqlQuery.setString(3, score);
            sqlQuery.setString(4, rating);
            sqlQuery.setString(5, director_fname);
            sqlQuery.setString(6, director_lname);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addMovie exception");
			e.printStackTrace();
		}

		String result = "Movie added: " + title;
		System.out.println(result);
	}

	public ArrayList<MovieBean> getmovies() {
		if (this.movies.size() > 0) {
			return this.movies;
		}

		this.movies = new ArrayList<MovieBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectMovie)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getmMovies exception");			e.printStackTrace();
		}

		return this.movies;
	}

	public void setScore(String title, float newScore) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_updateScore)) {
			sqlQuery.setFloat(1, newScore);
            sqlQuery.setString(1, title);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("setScore exception");
			e.printStackTrace();
		}

		String result = "Set new score: " + newScore + " for " + title;
		System.out.println(result);
	}

	public void deleteMovie(String title) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_deleteMovie)) {
			sqlQuery.setString(1, title);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addMovie exception");
			e.printStackTrace();
		}

		String result = "Movie deleted: " + title;
		System.out.println(result);
	}

	public String getBeansAsJSON() {
		String beansContent = "";
		for (MovieBean MovieBean : this.movies) {
			beansContent += MovieBean.toJson() + ",";
		}
		
		String result = "{" + jsonHelper.toJsonArray("Movie", beansContent) + "}";
		return result;

	}


	private MovieBean buildMovieBean (ResultSet resultSet) {
		MovieBean MovieBean = new MovieBean();

		try {
			MovieBean.setId(resultSet.getInt("movie_id"));
			MovieBean.setTitle(resultSet.getString("title"));
			MovieBean.setRuntime(resultSet.getInt("runtime"));
			MovieBean.setScore(resultSet.getFloat("score"));
            MovieBean.setRating(resultSet.getString("rating"));
            MovieBean.setDirectorId(resultSet.getInt("director_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return MovieBean;
	}

	private void buildMovieBeans(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.movies.add(buildMovieBean(resultSet));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet resultSet = query.executeQuery()) {
			buildMovieBeans(resultSet);
		} catch (SQLException e) {
			System.out.println("getMovie exception for result set");
			e.printStackTrace();
		}

	}
}
