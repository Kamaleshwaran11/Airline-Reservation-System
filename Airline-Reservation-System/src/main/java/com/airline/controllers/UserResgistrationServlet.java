package com.airline.controllers;

import com.airline.dao.UserDAO;
import com.airline.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        boolean isUserRegistered = userDAO.registerUser(user);
        if (isUserRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration Failed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
