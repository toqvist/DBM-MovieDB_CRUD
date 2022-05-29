package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DbConnect();

        // DumpTable(conn);

        // ListActors(conn);

        // ArrayList<actorBean> myActors = GetActors(conn);
        // for (actorBean ab : myActors) {
        // System.out.println(ab.getId());
        // System.out.println(ab.toString());
        // }

        conn.close();
    }

    private static Connection DbConnect() {
        String constr = "jdbc:mysql://localhost:3306/moviedb";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(constr, "root", "tobiastobias123");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("databas kan ej anslutas");
            e.printStackTrace();
        }

        System.out.println("databas ansluten");

        return conn;
    }

    private static void DumpTable(Connection cn) {
        String qry = "select * from actor";

        try {
            PreparedStatement myQry = cn.prepareStatement(qry);
            ResultSet rs = myQry.executeQuery();

            while (rs.next()) { // rows
                int numCols = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= numCols; i++) { // columns
                    String colName = rs.getMetaData().getColumnName(i);
                    System.out.println("Kolumnnamn: " + colName);
                    System.out.println("Data: " + rs.getObject(i));
                }
                System.out.println("new record");
            }
        } catch (SQLException e) {
            System.out.println("dump table exception");
            e.printStackTrace();
        }

    }

    private static void ListActors(Connection cn) {
        String qry = "select actor_name, actor_age, hometown from actor";

        try {
            PreparedStatement myQry = cn.prepareStatement(qry);
            ResultSet rs = myQry.executeQuery();
            while (rs.next()) { // rows
                String actorName = rs.getString("actor_name");
                int actorAge = rs.getInt("actor_age");
                String hometown = rs.getString("hometown");

                String pattern = "Namn = %s, Ålder = %d, Stad = %s";
                String text = String.format(pattern, actorName, actorAge, hometown);

                System.out.println(text);
            }
        } catch (SQLException e) {
            System.out.println("list actors exception");
            e.printStackTrace();
        }

    }

    // private static ArrayList<actorBean> GetActors(Connection cn) {
    // String qry = "select * from actor";

    // ArrayList<actorBean> actors = new ArrayList<actorBean>();

    // try {
    // PreparedStatement myQry = cn.prepareStatement(qry);
    // ResultSet rs = myQry.executeQuery();
    // while(rs.next()) { // rows
    // actorBean ab = new actorBean();
    // ab.setId(rs.getInt("actor_id"));
    // ab.setAge(rs.getInt("actor_age"));
    // ab.setName(rs.getString("actor_name"));
    // ab.setHometown(rs.getString("hometown"));
    // ab.setAddressId(rs.getInt("address_id"));

    // actors.add(ab);
    // }
    // } catch (SQLException e) {
    // System.out.println("list actors exception");
    // e.printStackTrace();
    // }

    // return actors;
    // }
}