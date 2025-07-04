package projects.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import projects.exception.DbException;

public class DbConnection {
    private static final String HOST = "localhost";
    private static final String PASSWORD = "thundercloud25@"; 
    private static final int PORT = 3306;
    private static final String SCHEMA = "projects";
    private static final String USER = "projects";

    public static Connection getConnection() {
       
        String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&allowPublicKeyRetrieval=true&useSSL=false",
                HOST, PORT, SCHEMA, USER, PASSWORD);

        try {
            Connection conn = DriverManager.getConnection(uri);
            //System.out.println("Connection to database successful!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            throw new DbException(e);
        }
    }
}


