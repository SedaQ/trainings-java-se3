package com.trainings.jdbc.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pavel Seda
 */
public class DBConnectionDriverManager {

    private static volatile Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnectionDriverManager.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jprog2", "postgres", "postgre");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

}
