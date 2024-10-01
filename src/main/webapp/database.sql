CREATE DATABASE api_portal;
USE api_portal;

CREATE TABLE user_accounts (
  account_id INT AUTO_INCREMENT PRIMARY KEY,
  account_username VARCHAR(50) UNIQUE NOT NULL,
  account_password VARCHAR(100) NOT NULL,
  account_email VARCHAR(100) NOT NULL,
  account_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE api_credentials (
  credential_id INT AUTO_INCREMENT PRIMARY KEY,
  account_id INT,
  api_token VARCHAR(100),
  api_status VARCHAR(20),
  credential_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (account_id) REFERENCES user_accounts(account_id)
);
