package com.api.developer.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.api.developer.model.APICredentials;
import com.api.developer.repository.ApiKeyRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ApiKeyRepository apiKeyRepo;

    @Override
    public void init() throws ServletException {
        apiKeyRepo = new ApiKeyRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<APICredentials> apiKeys = apiKeyRepo.getAllApiKeys();
            request.setAttribute("apiKeys", apiKeys);
            request.getRequestDispatcher("dashboard.html").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "An error occurred while retrieving API keys.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                APICredentials newApiKey = new APICredentials();
                newApiKey.setAccountId(1); 
                newApiKey.setApiToken(apiKeyRepo.generateApiKey());
                newApiKey.setApiStatus("active");
                newApiKey.setCredentialCreatedAt(new Timestamp(System.currentTimeMillis()));
                apiKeyRepo.saveApiKey(newApiKey);
                response.sendRedirect("ApiKeyServlet");
            } else if ("delete".equals(action)) {
                int credentialId = Integer.parseInt(request.getParameter("id"));
                apiKeyRepo.deleteApiKey(credentialId);
                response.sendRedirect("ApiKeyServlet");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "An error occurred while processing the request.");
        }
    }

}
