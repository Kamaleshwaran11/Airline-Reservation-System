package com.airline.controllers;

import com.airline.dao.FlightDAO;
import com.airline.models.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchFlights")
public class FlightSearchServlet extends HttpServlet {

    private FlightDAO flightDAO;

    @Override
    public void init() {
        flightDAO = new FlightDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String travelDate = request.getParameter("travelDate");

        // Fetch matching flights from the database
        List<Flight> flights = flightDAO.searchFlights(source, destination, travelDate);

        // Set the flights list as a request attribute to be used in the JSP
        request.setAttribute("flights", flights);
        request.setAttribute("source", source);
        request.setAttribute("destination", destination);
        request.setAttribute("travelDate", travelDate);

        // Forward the request to flightResults.jsp for displaying the search results
        request.getRequestDispatcher("flightResults.jsp").forward(request, response);
    }
}