package com.pahanaedu.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.sql.*;

import static org.junit.Assert.*;

public class DBConnectionTest {

    private static String originalUrl;
    private static String originalUser;
    private static String originalPassword;

    @BeforeClass
    public static void saveOriginalConfig() throws Exception {
        Field urlField = DBConnection.class.getDeclaredField("URL");
        urlField.setAccessible(true);
        originalUrl = (String) urlField.get(null);

        Field userField = DBConnection.class.getDeclaredField("USER");
        userField.setAccessible(true);
        originalUser = (String) userField.get(null);

        Field passField = DBConnection.class.getDeclaredField("PASSWORD");
        passField.setAccessible(true);
        originalPassword = (String) passField.get(null);
    }

    @AfterClass
    public static void restoreOriginalConfig() throws Exception {
        Field urlField = DBConnection.class.getDeclaredField("URL");
        urlField.setAccessible(true);
        urlField.set(null, originalUrl);

        Field userField = DBConnection.class.getDeclaredField("USER");
        userField.setAccessible(true);
        userField.set(null, originalUser);

        Field passField = DBConnection.class.getDeclaredField("PASSWORD");
        passField.setAccessible(true);
        passField.set(null, originalPassword);
    }

    public static Connection getConnection() {
        return null;
    }

    @Test
    public void testGetConnectionAndClose() throws SQLException {
        Connection conn = DBConnection.getConnection();
        assertNotNull(conn);
        assertTrue(conn.isValid(2));
        DBConnection.closeConnection(conn);
    }

    @Test
    public void testCloseConnectionWithNull() {
        DBConnection.closeConnection(null); // should not throw
    }

    @Test
    public void testTestConnectionSuccess() {
        assertTrue(DBConnection.testConnection());
    }

    @Test
    public void testTestConnectionFailureBranch() throws Exception {
        // Force invalid URL to trigger SQLException
        Field urlField = DBConnection.class.getDeclaredField("URL");
        urlField.setAccessible(true);
        urlField.set(null, "jdbc:invalid:url");

        assertFalse(DBConnection.testConnection());
    }

    @Test
    public void testCloseConnectionWithException() {
        Connection badConnection = new Connection() {
            @Override public void close() throws SQLException { throw new SQLException("boom"); }
            @Override public boolean isValid(int timeout) { return true; }
            @Override public <T> T unwrap(Class<T> iface) { return null; }
            @Override public boolean isWrapperFor(Class<?> iface) { return false; }
            @Override public Statement createStatement() { return null; }
            @Override public PreparedStatement prepareStatement(String sql) { return null; }
            @Override public CallableStatement prepareCall(String sql) { return null; }
            @Override public String nativeSQL(String sql) { return null; }
            @Override public void setAutoCommit(boolean autoCommit) {}
            @Override public boolean getAutoCommit() { return false; }
            @Override public void commit() {}
            @Override public void rollback() {}
            @Override public boolean isClosed() { return false; }
            @Override public DatabaseMetaData getMetaData() { return null; }
            @Override public void setReadOnly(boolean readOnly) {}
            @Override public boolean isReadOnly() { return false; }
            @Override public void setCatalog(String catalog) {}
            @Override public String getCatalog() { return null; }
            @Override public void setTransactionIsolation(int level) {}
            @Override public int getTransactionIsolation() { return 0; }
            @Override public SQLWarning getWarnings() { return null; }
            @Override public void clearWarnings() {}
            @Override public Statement createStatement(int a, int b) { return null; }
            @Override public PreparedStatement prepareStatement(String s, int a, int b) { return null; }
            @Override public CallableStatement prepareCall(String s, int a, int b) { return null; }
            @Override public java.util.Map<String, Class<?>> getTypeMap() { return null; }
            @Override public void setTypeMap(java.util.Map<String, Class<?>> map) {}
            @Override public void setHoldability(int holdability) {}
            @Override public int getHoldability() { return 0; }
            @Override public Savepoint setSavepoint() { return null; }
            @Override public Savepoint setSavepoint(String name) { return null; }
            @Override public void rollback(Savepoint savepoint) {}
            @Override public void releaseSavepoint(Savepoint savepoint) {}
            @Override public Statement createStatement(int a, int b, int c) { return null; }
            @Override public PreparedStatement prepareStatement(String s, int a, int b, int c) { return null; }
            @Override public CallableStatement prepareCall(String s, int a, int b, int c) { return null; }
            @Override public PreparedStatement prepareStatement(String s, int autoKeys) { return null; }
            @Override public PreparedStatement prepareStatement(String s, int[] cols) { return null; }
            @Override public PreparedStatement prepareStatement(String s, String[] cols) { return null; }
            @Override public Clob createClob() { return null; }
            @Override public Blob createBlob() { return null; }
            @Override public NClob createNClob() { return null; }
            @Override public SQLXML createSQLXML() { return null; }
            @Override public void setClientInfo(String n, String v) {}
            @Override public void setClientInfo(java.util.Properties p) {}
            @Override public String getClientInfo(String n) { return null; }
            @Override public java.util.Properties getClientInfo() { return null; }
            @Override public Array createArrayOf(String t, Object[] e) { return null; }
            @Override public Struct createStruct(String t, Object[] a) { return null; }
            @Override public void setSchema(String schema) {}
            @Override public String getSchema() { return null; }
            @Override public void abort(java.util.concurrent.Executor ex) {}
            @Override public void setNetworkTimeout(java.util.concurrent.Executor ex, int ms) {}
            @Override public int getNetworkTimeout() { return 0; }
        };

        DBConnection.closeConnection(badConnection); // triggers catch + println
    }

    @Test
    public void testMainMethod() {
        DBConnection.main(new String[]{});
    }
}
