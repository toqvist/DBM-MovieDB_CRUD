package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.ActorshipBean;
import com.example.beans.MovieBean;
import com.example.beans.PersonBean;
import com.example.helpers.jsonHelper;

public class Actorship {
	private Connection connection;

	private ArrayList<ActorshipBean> actorships;
	
    //CALL sp_add_actorship('Samuel', 'Jackson', 'Pulp Fiction', 'Jules Winnifield');

    private String query_createActorship = "CALL sp_add_actorship(?,?,?,?);";
    private String query_selectActorships = "SELECT * FROM actors_in_movie;";
    private String query_changeRole = "UPDATE actor_in_movie SET character_role = ? WHERE character_role = ?;";
    private String query_deleteActorship = "DELETE FROM actor_in_movie WHERE (movie_title AND fname = ? AND lname = ?);";

    Movie movie;
    Person person;

	public Actorship(Connection connection, Movie movie, Person person) {
		this.connection = connection;
		this.actorships = new ArrayList<ActorshipBean>();
        this.movie = movie;
        this.person = person;
		getActorships();
	}

	public void addActorship(String fname, String lname, String movie_title, String character_role) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_createActorship)) {
			sqlQuery.setString(1, fname);
            sqlQuery.setString(2, lname);
            sqlQuery.setString(3, movie_title);
            sqlQuery.setString(4, character_role);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("addActorship exception");
			e.printStackTrace();
		}

		String result = "Actorship added: " + fname + " " + lname + " in " + movie_title + " as " + character_role;
		System.out.println(result);
	}

	public ArrayList<ActorshipBean> getActorships() {
		if (this.actorships.size() > 0) {
			return this.actorships;
		}

		this.actorships = new ArrayList<ActorshipBean>();
		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectActorships)) {
			runQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("getmActorships exception");			
            e.printStackTrace();
		}

		return this.actorships;
	}

	public void setScore(String newRole, String oldRole) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_changeRole)) {
            sqlQuery.setString(1, newRole);
            sqlQuery.setString(2, oldRole);
			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();
		} catch (SQLException e) {
			System.out.println("changeRole exception");
			e.printStackTrace();
		}

		String result = "Role changed: " + oldRole + " is now " + newRole;
		System.out.println(result);
	}

	public void deleteActorship(String fname, String lname, String movieTitle) {

		try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_deleteActorship)) {
			sqlQuery.setString(1, fname);
            sqlQuery.setString(2, fname);
            sqlQuery.setString(3, movieTitle);

			
			System.out.println(sqlQuery);

			sqlQuery.executeUpdate();

		} catch (SQLException e) {
			System.out.println("deleteActorship exception");
			e.printStackTrace();
		}

		String result = "Actorship deleted: " + fname + " " + lname + " in " + movieTitle;
		System.out.println(result);
	}

	public String getBeansAsJSON() {
		String beansContent = "";
		for (ActorshipBean actorshipBean : this.actorships) {
			beansContent += actorshipBean.toJson() + ",";
		}
		
		String result = "{" + jsonHelper.toJsonArray("actorship", beansContent) + "}";
		return result;

	}


	private ActorshipBean buildActorshipBean (ResultSet resultSet) {
		ActorshipBean actorshipBean = new ActorshipBean();

		try {
            //Find references to the related PersonBean and MovieBean objects

            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String movieTitle = resultSet.getString("movie_title");

            PersonBean personBean = person.findPersonBean(fname, lname);
            MovieBean movieBean = movie.findMovieBean(movieTitle);

            actorshipBean.setActor(personBean);
            actorshipBean.setMovie(movieBean);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actorshipBean;
	}

	private void buildActorshipBeans(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) { // rows
			this.actorships.add(buildActorshipBean(resultSet));
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