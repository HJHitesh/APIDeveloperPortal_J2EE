package com.api.developer.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.api.developer.model.APICredentials;

public class ApiKeyRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/api_portal";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public List<APICredentials> getAllApiKeys() throws SQLException {
        List<APICredentials> apiKeys = new ArrayList<>();
        String query = "SELECT * FROM api_credentials"; // Assuming a table named api_credentials

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	APICredentials apiKey = new APICredentials();
                apiKey.setCredentialId(rs.getInt("credential_id"));
                apiKey.setAccountId(rs.getInt("account_id"));
                apiKey.setApiToken(rs.getString("api_token"));
                apiKey.setApiStatus(rs.getString("api_status"));
                apiKey.setCredentialCreatedAt(rs.getTimestamp("credential_created_at"));
                apiKeys.add(apiKey);
            }
        }
        return apiKeys;
    }

    public String generateApiKey() {
        // Logic to generate a unique API key
        return "API_KEY_" + System.currentTimeMillis(); // Example key generation
    }

    public void saveApiKey(APICredentials apiKey) throws SQLException {
        String query = "INSERT INTO api_credentials (account_id, api_token, api_status, credential_created_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, apiKey.getAccountId());
            stmt.setString(2, apiKey.getApiToken());
            stmt.setString(3, apiKey.getApiStatus());
            stmt.setTimestamp(4, apiKey.getCredentialCreatedAt());
            stmt.executeUpdate();
        }
    }

    public void deleteApiKey(int credentialId) throws SQLException {
        String query = "DELETE FROM api_credentials WHERE credential_id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, credentialId);
            stmt.executeUpdate();
        }
    }
}
