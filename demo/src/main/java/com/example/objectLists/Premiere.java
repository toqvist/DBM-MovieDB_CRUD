package com.example.objectLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.beans.MovieBean;
import com.example.beans.PremiereBean;
import com.example.helpers.jsonHelper;

public class Premiere {

    private Connection connection;
    private ArrayList<PremiereBean> premieres;
    private Movie movie;

	private String query_createPremiere = "CALL sp_add_premiere(?, ?, ?);";
    private String query_selectPremiere = "SELECT * FROM oldest_movie WHERE title = ? ;";
	private String query_deletePremiere = "DELETE FROM premiere WHERE (title = ? AND date = ? AND country = ?);";

	//FOR PREMIERES
	//Find moviebean
	//Add premierebean to moviebean.premieres

    public Premiere (Connection connection, Movie movie) {
		this.connection = connection;
		this.premieres = new ArrayList<PremiereBean>();
        this.movie = movie;
		getPremieres();
	}

    public void addPremiere(String title, String date, String country) {

        try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_createPremiere)) {
            sqlQuery.setString(1, title);
            sqlQuery.setString(2, date);
            sqlQuery.setString(3, country);
        

            System.out.println(sqlQuery);

            sqlQuery.executeUpdate();

        } catch (SQLException e) {
            System.out.println("addPremiere exception");
            e.printStackTrace();
        }

        String result = "Premiere added: " + title + " " + date + " " + country;
        System.out.println(result);
    }

    public ArrayList<PremiereBean> getPremieres() {
        if (this.premieres.size() > 0) {
            return this.premieres;
        }

        this.premieres = new ArrayList<PremiereBean>();
        try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_selectPremiere)) {
            runQuery(sqlQuery);
        } catch (SQLException e) {
            System.out.println("getPremieres exception");
            e.printStackTrace();
        }

        return this.premieres;
    }

    
    public void deletePremiere(String title, String date, String country) {

        try (PreparedStatement sqlQuery = this.connection.prepareStatement(query_deletePremiere)) {
            sqlQuery.setString(1, title);
            sqlQuery.setString(2, date);
            sqlQuery.setString(3, country);

            System.out.println(sqlQuery);

            sqlQuery.executeUpdate();

        } catch (SQLException e) {
            System.out.println("deletePremiere exception");
            e.printStackTrace();
        }

        String result = "Premiere deleted: " + title + " " + date + " " + country;
        System.out.println(result);
    }

    public String getBeansAsJSON() {
        String beansContent = "";
        for (PremiereBean premiereBean : this.premieres) {
            beansContent += premiereBean.toJson() + ",";
        }

        String result = "{" + jsonHelper.toJsonArray("Movie", beansContent) + "}";
        return result;

    }

    private PremiereBean buildPremiereBean(ResultSet resultSet) {

        PremiereBean premiereBean = null;

        try {
            //Find the moviebean that has a premiere
            MovieBean movieBean = movie.findMovieBean(resultSet.getString("title"));
            //Add premiere to correct moviebean, save the returned premierebean
            premiereBean = movieBean.addPremiere(resultSet.getString("date"), resultSet.getString("country"));
        

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return premiereBean;
    }

    private void buildMovieBeans(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) { // rows
            this.premieres.add(buildPremiereBean(resultSet));
        }
    }

    private void runQuery(PreparedStatement query) {
        try (ResultSet resultSet = query.executeQuery()) {
            buildMovieBeans(resultSet);
        } catch (SQLException e) {
            System.out.println("getPremiere exception");
            e.printStackTrace();
        }
    }

}
