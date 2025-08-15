package com.pahanaedu.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectionTest {

    @Test
    public void testGetConnection() {
        try (Connection conn = DBConnection.getConnection()) {
            assertNotNull("Connection should not be null", conn);
            assertFalse("Connection should not be closed", conn.isClosed());
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testCloseConnection() {
        try {
            Connection conn = DBConnection.getConnection();
            assertFalse(conn.isClosed());
            DBConnection.closeConnection(conn);
            assertTrue("Connection should be closed after closeConnection()", conn.isClosed());
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    public void testTestConnection() {
        boolean result = DBConnection.testConnection();
        assertTrue("testConnection() should return true if DB is reachable", result);
    }
}
