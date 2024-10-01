package com.api.developer.model;

import java.sql.Timestamp;

public class UserAccount {

	private int accountId;
	private String accountUsername;
	private String accountPassword;
	private String accountEmail;
	private Timestamp accountCreatedAt;
	

	public UserAccount(int accountId, String accountUsername, String accountPassword, String accountEmail) {
		this.accountId = accountId;
		this.accountUsername = accountUsername;
		this.accountPassword = accountPassword;
		this.accountEmail = accountEmail;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountUsername() {
		return accountUsername;
	}

	public void setAccountUsername(String accountUsername) {
		this.accountUsername = accountUsername;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public Timestamp getAccountCreatedAt() {
		return accountCreatedAt;
	}

	public void setAccountCreatedAt(Timestamp accountCreatedAt) {
		this.accountCreatedAt = accountCreatedAt;
	}

}
