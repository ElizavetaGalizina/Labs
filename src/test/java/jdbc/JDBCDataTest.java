package jdbc;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JDBCDataTest {

    @Test
    void readAll() throws SQLException, ClassNotFoundException {
        JDBCData jdbcData = new JDBCData();
        jdbcData.readAll();
    }

}