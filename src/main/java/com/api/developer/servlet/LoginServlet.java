package com.api.developer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.api.developer.model.UserAccount;
import com.api.developer.repository.UserRepository;

/**
 * Servlet implementation class RegisterServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository userDAO;

	@Override
	public void init() throws ServletException {
		// Initialize the DAO layer
		userDAO = new UserRepository();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Validate user credentials
		try {
			boolean isValidUser = userDAO.validateUser(username, password);
			if (isValidUser) {
				 System.out.println(" Login User Successfull");
				// Redirect to a welcome page or dashboard if login is successful
				response.sendRedirect("dashboard.html");
			} else {
				// If login fails, redirect back to login page with error
				request.setAttribute("error", "Invalid username or password.");
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while logging in.");
		}
	}

}
