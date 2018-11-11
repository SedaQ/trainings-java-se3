package com.trainings.jdbc.dbconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * It is preferable to use DataSource over DriverManager because it maintains the connection pool, so you can reusing the same connections instead of the need to get new one each time.
 * <p>
 * Hikari Benchmark: https://github.com/brettwooldridge/HikariCP-benchmark
 * </p>
 *
 * @author Pavel Seda
 */
public class DBConnectionHikariDataSource {

    private static HikariConfig config = new HikariConfig();
    private static volatile HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/jprog2");
        config.setUsername("postgres");
        config.setPassword("postgre");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DBConnectionHikariDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            synchronized (DBConnectionHikariDataSource.class) {
                if (ds == null) {
                    try {
                        ds.getConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ds.getConnection();
    }

}
