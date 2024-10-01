package com.api.developer.repository;

import java.sql.SQLException;

import com.api.developer.model.UserAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
	
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/api_portal";
    private static final String JDBC_USER = "root"; 
    private static final String JDBC_PASSWORD = "";
    
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // Declare the driver globally

    static {
        try {
            Class.forName(JDBC_DRIVER); // Load the driver once when the class is loaded
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC Driver not found.");
        }
    }
    

    public boolean saveUser(UserAccount user) throws SQLException, ClassNotFoundException {
       

        String query = "INSERT INTO user_accounts (account_id, account_username, account_password, account_email, account_created_at) VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, user.getAccountId());
            stmt.setString(2, user.getAccountUsername());
            stmt.setString(3, user.getAccountPassword());
            stmt.setString(4, user.getAccountEmail());

            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
    
    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException {
       
    	
    	String sql = "SELECT * FROM user_accounts WHERE account_username = ? AND account_password = ?";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found
        }
    }


}
