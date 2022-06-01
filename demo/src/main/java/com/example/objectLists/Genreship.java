package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.ActorshipBean;
import com.example.beans.GenreBean;
import com.example.beans.GenreshipBean;
import com.example.beans.MovieBean;
import com.example.beans.PersonBean;
import com.example.helpers.jsonHelper;

public class Genreship {
	private Connection connection;

	private ArrayList<GenreshipBean> genreships;
	
    private String query_createGenreship = "CALL sp_add_genreship(?,?);";
    private String query_selectGenreships = "SELECT * FROM movie_in_genre;";
    private String query_deleteGenreship = "DELETE FROM genreship WHERE (movie_id = ? AND genre_name = ?);";

    Movie movie;
    Genre genre;

	public Genreship(Connection connection, Genre genre, Movie movie) {
		this.connection = connection;
		this.genreships = new ArrayList<GenreshipBean>();
        this.genre = genre;
        this.movie = movie;
		getGenreships();
	}

	public void createGenreship(String movieTitle, String genre) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_createGenreship)) {
			
            sqlQuery.setString(1, movieTitle);
            sqlQuery.setString(2, genre);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addGenreship exception");
			e.printStackTrace();
		}

		String result = "Genreship added: " + movieTitle + " is now a " + genre + " movie";
		System.out.println(result);
	}

	public ArrayList<GenreshipBean> getGenreships() {
		if (this.genreships.size() > 0) {
			return this.genreships;
		}

		this.genreships = new ArrayList<GenreshipBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectGenreships)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getGenreship exception");			
            e.printStackTrace();
		}

		return this.genreships;
	}

	public void deleteGenreship( String movieTitle, String genreName) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_deleteGenreship)) {
			
			MovieBean movieBean = movie.findMovieBean(movieTitle);
            GenreBean genreBean = genre.findGenreBean(genreName);
            
			sqlQuery.setInt(1, movieBean.getId());
            sqlQuery.setString(2, genreBean.getName());

			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("deleteActorship exception");
			e.printStackTrace();
		}

		String result = "Genreship deleted: " + movieTitle + " is no longer a " + genreName + " movie";
		System.out.println(result);
	}

	public String getBeansAsJSON() {
		String beansContent = "";
		for (GenreshipBean genreshipBean : this.genreships) {
			beansContent += genreshipBean.toJson() + ",";
		}
		
		String result = "{" + jsonHelper.toJsonArray("genreship", beansContent) + "}";
		return result;

	}


	private GenreshipBean buildActorshipBean (ResultSet resultSet) {
		GenreshipBean genreshipBean = new GenreshipBean();

		try {

            String genreName = resultSet.getString("genre_name");
            String movieTitle = resultSet.getString("title");

			GenreBean genreBean = genre.findGenreBean(genreName);
            MovieBean movieBean = movie.findMovieBean(movieTitle);

            genreshipBean.setGenre(genreBean);
            genreshipBean.setMovie(movieBean);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return genreshipBean;
	}

	private void buildActorshipBeans(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.genreships.add(buildActorshipBean(resultSet));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet resultSet = query.executeQuery()) {
			buildActorshipBeans(resultSet);
		} catch (SQLException e) {
			System.out.println("getactorsh exception for result set");
			e.printStackTrace();
		}

	}
}