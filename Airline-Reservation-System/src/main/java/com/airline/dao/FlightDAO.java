package com.airline.dao;

import com.airline.database.DatabaseConnection;
import com.airline.models.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    public static List<Flight> searchFlights(String origin, String destination, String departureDate) {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM flights WHERE origin = ? AND destination = ? AND DATE(departure_time) = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, origin);
            statement.setString(2, destination);
            statement.setString(3, departureDate);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight(
                        resultSet.getInt("id"),
                        resultSet.getString("origin"),
                        resultSet.getString("destination"),
                        resultSet.getTimestamp("departure_time"),
                        resultSet.getTimestamp("arrival_time")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
