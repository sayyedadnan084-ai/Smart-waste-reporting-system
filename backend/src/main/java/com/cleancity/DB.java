package com.cleancity;
import java.sql.*;
public class DB {
  public static Connection get() throws SQLException {
    String url=System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/cleancity?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    String user=System.getenv().getOrDefault("DB_USER","root");
    String pass=System.getenv().getOrDefault("DB_PASSWORD","root");
    return DriverManager.getConnection(url,user,pass);
  }
}
