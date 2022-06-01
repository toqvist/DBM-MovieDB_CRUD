package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.GenreBean;
import com.example.helpers.jsonHelper;

public class Genre {
	private Connection connection;
	private ArrayList<GenreBean> Genres;
	
    private String query_createGenre = "INSERT INTO genre(genre_name) VALUES (?);";
    private String query_selectGenre = "SELECT * FROM genre;";
    private String query_renameGenre = "UPDATE genre SET  genre_name = ? WHERE genre.genre_name = ?;";
    private String query_deleteGenre = "DELETE FROM genre WHERE genre.genre_name = ?;";
 

	public Genre(Connection connection) {
		this.connection = connection;
		this.Genres = new ArrayList<GenreBean>();
		getGenres();
	}

	public void addGenre(String genre) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_createGenre)) {
			
            sqlQuery.setString(1, genre);
			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addGenre exception");
			e.printStackTrace();
		}

		String result = "Genre added: " + genre;
		System.out.println(result);
	}

	public ArrayList<GenreBean> getGenres() {
		if (this.Genres.size() > 0) {
			return this.Genres;
		}

		this.Genres = new ArrayList<GenreBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectGenre)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getmGenres exception");			e.printStackTrace();
		}

		return this.Genres;
	}

	public void renameGenre(String newGenre, String oldGenre) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_renameGenre)) {
            sqlQuery.setString(1, newGenre);
            sqlQuery.setString(2, oldGenre);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("setScore exception");
			e.printStackTrace();
		}

		String result = "Renamed: " + oldGenre + " to:" + newGenre;
		System.out.println(result);
	}

	public void deleteGenre(String genre) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_deleteGenre)) {
			sqlQuery.setString(1, genre);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("deleteGenre exception");
			e.printStackTrace();
		}

		String result = "Genre deleted: " + genre;
		System.out.println(result);
	}

	public String getBeansAsJSON() {
		String beansContent = "";
		for (GenreBean genreBean : this.Genres) {
			beansContent += genreBean.toJson() + ",";
		}
		
		String result = "{" + jsonHelper.toJsonArray("Genre", beansContent) + "}";
		return result;

	}


	private GenreBean buildGenreBean (ResultSet resultSet) {
		GenreBean GenreBean = new GenreBean();

		try {
			GenreBean.setName(resultSet.getString("genre_name"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return GenreBean;
	}

	private void buildGenreBeans(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.Genres.add(buildGenreBean(resultSet));
		}
	}

	private void runQuery(PreparedStatement query) {
		try (ResultSet resultSet = query.executeQuery()) {
			buildGenreBeans(resultSet);
		} catch (SQLException e) {
			System.out.println("getGenre exception for result set");
			e.printStackTrace();
		}

	}

}
