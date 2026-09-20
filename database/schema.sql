CREATE DATABASE IF NOT EXISTS cleancity CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cleancity;
CREATE TABLE IF NOT EXISTS users (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 email VARCHAR(150) NOT NULL UNIQUE,
 password_hash VARCHAR(100) NOT NULL,
 role ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS complaints (
 id INT AUTO_INCREMENT PRIMARY KEY,
 user_id INT NOT NULL,
 description VARCHAR(1000) NOT NULL,
 location VARCHAR(255) NOT NULL,
 status ENUM('Pending','In Progress','Completed') NOT NULL DEFAULT 'Pending',
 photo LONGBLOB NULL,
 photo_type VARCHAR(100) NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
-- Demo admin: admin@cleancity.com / admin123
INSERT INTO users(name,email,password_hash,role) VALUES ('CleanCity Admin','admin@cleancity.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN')
ON DUPLICATE KEY UPDATE email=email;
