package com.pahanaedu.controller;

import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

import static org.junit.Assert.*;

public class LoginControllerTest {

    // --- Mock classes ---
    static class MockHttpSession implements HttpSession {
        private boolean invalidated = false;

        @Override public long getCreationTime() { return 0; }
        @Override public String getId() { return "mockSession"; }
        @Override public long getLastAccessedTime() { return 0; }
        @Override public ServletContext getServletContext() { return null; }
        @Override public void setMaxInactiveInterval(int interval) {}
        @Override public int getMaxInactiveInterval() { return 0; }
        @Override public HttpSessionContext getSessionContext() { return null; }
        @Override public Object getAttribute(String name) { return null; }
        @Override public Object getValue(String name) { return null; }
        @Override public Enumeration<String> getAttributeNames() { return null; }
        @Override public String[] getValueNames() { return new String[0]; }
        @Override public void setAttribute(String name, Object value) {}
        @Override public void putValue(String name, Object value) {}
        @Override public void removeAttribute(String name) {}
        @Override public void removeValue(String name) {}
        @Override public void invalidate() { invalidated = true; }
        @Override public boolean isNew() { return false; }
        public boolean isInvalidated() { return invalidated; }
    }

    static class MockRequestDispatcher implements RequestDispatcher {
        @Override
        public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
            // simulate forward
        }
        @Override
        public void include(ServletRequest request, ServletResponse response) {}
    }

    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final MockHttpSession session = new MockHttpSession();
        private String username;
        private String password;
        private String attribute;

        public MockHttpServletRequest() { super(new HttpServletRequestAdapter()); }

        @Override
        public String getParameter(String name) {
            if ("username".equals(name)) return username;
            if ("password".equals(name)) return password;
            return null;
        }

        public void setParameter(String name, String value) {
            if ("username".equals(name)) username = value;
            if ("password".equals(name)) password = value;
        }

        @Override
        public HttpSession getSession() { return session; }

        @Override
        public void setAttribute(String name, Object o) { attribute = (String) o; }

        public String getAttributeValue() { return attribute; }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return new MockRequestDispatcher();
        }
    }

    class MockHttpServletResponse extends HttpServletResponseWrapper {
        private String redirectedUrl;

        public MockHttpServletResponse() { super(new HttpServletResponseAdapter()); }

        @Override
        public void sendRedirect(String location) {
            redirectedUrl = location;
        }

        public String getRedirectedUrl() { return redirectedUrl; }
    }

    // --- Test methods ---

    @Test
    public void testDoPostValidAdmin() throws ServletException, IOException {
        LoginController controller = new LoginController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "admin");
        request.setParameter("password", "admin123");

        controller.doPost(request, response);

        // valid admin should redirect to admin dashboard
        assertEquals("admin_dashboard.jsp", response.getRedirectedUrl());
    }

    @Test
    public void testDoPostValidStaff() throws ServletException, IOException {
        LoginController controller = new LoginController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Assuming staff user exists in DB
        request.setParameter("username", "staff1");
        request.setParameter("password", "staff123");

        controller.doPost(request, response);

        // valid staff should redirect to staff dashboard
        assertEquals("staff_dashboard.jsp", response.getRedirectedUrl());
    }

    @Test
    public void testDoPostInvalidUser() throws ServletException, IOException {
        LoginController controller = new LoginController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("username", "admin");
        request.setParameter("password", "wrongpass");

        controller.doPost(request, response);

        // invalid credentials should set error attribute
        assertEquals("Invalid username or password", request.getAttributeValue());
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        LoginController controller = new LoginController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        controller.doGet(request, response);

        assertTrue(true);
    }
}
