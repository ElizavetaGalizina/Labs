package jdbc;

import contracts.Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Подключение к базе данных.
 */
public class PostgresSQL {

    private static Logger logger = Logger.getLogger(PostgresSQL.class.getName());
    Connection connection;

    public PostgresSQL() {
    }

    public Connection connection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.info("PostgreSQL JDBC Driver is not found.");
            logger.log(Level.INFO, "Injection exception", e);
            return null;
        }
        String url = "jdbc:postgresql://localhost:5432/repository";
        String user = "postgres";
        String pass = "qwerty";
        this.connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

    public ResultSet getData() throws SQLException {
        Statement statement = this.connection.createStatement();
        String query = "select * from contact";
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    public void closeConnection () throws SQLException {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
