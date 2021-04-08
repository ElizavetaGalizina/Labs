package jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PostgresSQLTest {

    public static PostgresSQL postgresSQL;

    @Test
    public void shouldGetJdbcConnection() throws SQLException {
        postgresSQL = new PostgresSQL();
        try(Connection connection = postgresSQL.connection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

    @Before
    public void init() throws SQLException {
        postgresSQL = (PostgresSQL) postgresSQL.connection();
    }

    @After
    public void close() throws SQLException {
        postgresSQL.getConnection().close();
    }

}