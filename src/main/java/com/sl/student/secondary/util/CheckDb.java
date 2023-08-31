package com.sl.student.secondary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDb {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/StoredProducer";
        String username = "postgres";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String procedureCall = "{call get_movie_info_by_rating(?) }";
            CallableStatement callableStatement = connection.prepareCall(procedureCall);

            callableStatement.setInt(1, 5);
            System.out.println(callableStatement);
            ResultSet resultSet = callableStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                String movieName = resultSet.getString("movie_name");
                String movieDescription = resultSet.getString("movie_description");
                java.sql.Date movieReleaseDate = resultSet.getDate("movie_releasedate");
                int movieRatingResult = resultSet.getInt("movie_rating");
                String songTitle = resultSet.getString("title");
                String singer = resultSet.getString("singer");
                String songType = resultSet.getString("type_song");

                // Process the retrieved data
                System.out.println("Movie Name: " + movieName);
                System.out.println("Movie Description: " + movieDescription);
                System.out.println("Release Date: " + movieReleaseDate);
                System.out.println("Movie Rating: " + movieRatingResult);
                System.out.println("Song Title: " + songTitle);
                System.out.println("Singer: " + singer);
                System.out.println("Song Type: " + songType);
                System.out.println("-------------------------");
            }

            // Close resources
            resultSet.close();
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
