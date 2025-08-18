package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.dao.impl.ItemDAOImpl;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemSearchServletTest {

    private ItemSearchServlet servlet;
    private TestHttpServletRequest request;
    private TestHttpServletResponse response;

    @Before
    public void setUp() {
        servlet = new ItemSearchServlet();
        request = new TestHttpServletRequest();
        response = new TestHttpServletResponse();
    }

    @Test
    public void testDoGetReturnsItemsJson() throws ServletException, IOException, SQLException {
        // Set a search term
        request.setParameter("term", "book");

        // Optionally, pre-populate DB with some test items using DAO
        // Or rely on existing test DB data

        // Call servlet
        servlet.doGet(request, response);

        // Get response as string
        String jsonOutput = response.getOutput();

        // Basic assertions
        assertNotNull(jsonOutput);
        assertTrue(jsonOutput.startsWith("["));
        assertTrue(jsonOutput.endsWith("]"));

        // Optional: check if JSON contains expected fields
        assertTrue(jsonOutput.contains("\"id\""));
        assertTrue(jsonOutput.contains("\"name\""));
        assertTrue(jsonOutput.contains("\"price\""));
        assertTrue(jsonOutput.contains("\"stockQuantity\""));
    }

    // ----------- Helper classes to simulate HttpServletRequest/Response -----------

    static class TestHttpServletRequest extends HttpServletRequestWrapper {

        private final java.util.Map<String, String> parameters = new java.util.HashMap<>();

        public TestHttpServletRequest() {
            super(new HttpServletRequestAdapter());
        }

        public void setParameter(String name, String value) {
            parameters.put(name, value);
        }

        @Override
        public String getParameter(String name) {
            return parameters.get(name);
        }
    }

    static class TestHttpServletResponse extends HttpServletResponseWrapper {

        private final StringWriter stringWriter = new StringWriter();
        private final PrintWriter writer = new PrintWriter(stringWriter);

        public TestHttpServletResponse() {
            super(new HttpServletResponseAdapter());
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return writer;
        }

        public String getOutput() {
            writer.flush();
            return stringWriter.toString();
        }
    }
}
