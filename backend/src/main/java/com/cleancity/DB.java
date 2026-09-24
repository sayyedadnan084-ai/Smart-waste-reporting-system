
package com.cleancity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    public static Connection get() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        String url = System.getenv().getOrDefault(
            "DB_URL",
            "jdbc:mysql://localhost:3306/cleancity?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        );

        String user = System.getenv().getOrDefault("DB_USER", "root");
        String pass = System.getenv().getOrDefault("DB_PASSWORD", "root");

        Connection conn = DriverManager.getConnection(url, user, pass);
        
        // Sahi column name (password_hash) ke sath automatic table script
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL UNIQUE, " +
                    "password_hash VARCHAR(255) NOT NULL, " +
                    "role VARCHAR(50) DEFAULT 'USER')");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS complaints (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "description TEXT, " +
                    "location VARCHAR(255), " +
                    "status VARCHAR(50) DEFAULT 'PENDING', " +
                    "photo_url VARCHAR(555), " +
                    "user_id INT, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)");
        } catch (SQLException e) {
            System.out.println("Tables auto-creation log: " + e.getMessage());
        }

        return conn;
    }
}
