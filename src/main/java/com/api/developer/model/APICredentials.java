package com.api.developer.model;

import java.sql.Timestamp;

public class APICredentials {

	private int credentialId;
    private int accountId;
    private String apiToken;
    private String apiStatus;
    private Timestamp credentialCreatedAt;

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public Timestamp getCredentialCreatedAt() {
        return credentialCreatedAt;
    }

    public void setCredentialCreatedAt(Timestamp credentialCreatedAt) {
        this.credentialCreatedAt = credentialCreatedAt;
    }

}
