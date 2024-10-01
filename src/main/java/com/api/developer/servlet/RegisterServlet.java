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
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserRepository userDAO;

    @Override
    public void init() throws ServletException {
    	//Intializing the DOA layer
        userDAO = new UserRepository(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            // Redirect back to the form with an error message if fields are empty
            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        int userId = generateUniqueUserId();

        // Add the user Account
        UserAccount user = new UserAccount(userId,username, password, email);

        // Use the UserDAO to save the user in the database
        try {
            if (userDAO.saveUser(user)) {
                // Redirect to success page if registration succeeds
            	response.sendRedirect("login.html");
            	
            	 System.out.println(" Register User.");
            	
            } else {
            	 request.setAttribute("error", "Registration failed. Username might already be taken.");
                 request.getRequestDispatcher("register.html").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while registering the user.");
        }
    }
    
    private int generateUniqueUserId() {
        return (int) (Math.random() * 10000); 
    }

}
