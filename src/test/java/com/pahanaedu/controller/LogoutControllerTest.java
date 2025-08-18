package com.pahanaedu.controller;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;

public class LogoutControllerTest {

    private LogoutController servlet;
    private TestRequest request;
    private TestResponse response;

    @Before
    public void setUp() {
        servlet = new LogoutController();
        request = new TestRequest();
        response = new TestResponse();
    }

    @Test
    public void testDoGetWithExistingSession() throws ServletException, IOException {
        // Arrange
        TestSession session = new TestSession();
        request.setSession(session);

        // Act
        servlet.doGet(request, response);

        // Assert
        assertTrue(session.isInvalidated());
        assertEquals("/login.jsp", response.getRedirectedUrl());
    }

    @Test
    public void testDoGetWithoutSession() throws ServletException, IOException {
        // Arrange: no session set (getSession(false) returns null)

        // Act
        servlet.doGet(request, response);

        // Assert
        assertEquals("/login.jsp", response.getRedirectedUrl());
    }

    @Test
    public void testDoPostDelegatesToDoGet() throws ServletException, IOException {
        // Arrange
        TestSession session = new TestSession();
        request.setSession(session);

        // Act
        servlet.doPost(request, response);

        // Assert
        assertTrue(session.isInvalidated());
        assertEquals("/login.jsp", response.getRedirectedUrl());
    }

    // ---------- Fake Request/Response/Session Classes ----------

    private static class TestRequest extends HttpServletRequestWrapper {
        private HttpSession session;
        private String contextPath = "";

        public TestRequest() {
            super(new HttpServletRequestAdapter());
        }

        public void setSession(HttpSession session) {
            this.session = session;
        }

        @Override
        public HttpSession getSession(boolean create) {
            return session;
        }

        @Override
        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }
    }

    private static class TestResponse extends HttpServletResponseWrapper {
        private String redirectedUrl;

        public TestResponse() {
            super(new HttpServletResponseAdapter());
        }

        @Override
        public void sendRedirect(String location) {
            this.redirectedUrl = location;
        }

        public String getRedirectedUrl() {
            return redirectedUrl;
        }
    }

    private static class TestSession implements HttpSession {
        private boolean invalidated = false;

        @Override
        public void invalidate() {
            invalidated = true;
        }

        public boolean isInvalidated() {
            return invalidated;
        }

        // ---- Minimal stub methods (not used but required) ----
        @Override public Object getAttribute(String name) { return null; }
        @Override public void setAttribute(String name, Object value) {}
        @Override public void removeAttribute(String name) {}
        @Override public long getCreationTime() { return 0; }
        @Override public String getId() { return null; }
        @Override public long getLastAccessedTime() { return 0; }
        @Override public javax.servlet.ServletContext getServletContext() { return null; }
        @Override public void setMaxInactiveInterval(int interval) {}
        @Override public int getMaxInactiveInterval() { return 0; }
        @Override public javax.servlet.http.HttpSessionContext getSessionContext() { return null; }
        @Override public Object getValue(String name) { return null; }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override public String[] getValueNames() { return new String[0]; }
        @Override public void putValue(String name, Object value) {}
        @Override public void removeValue(String name) {}
        @Override public boolean isNew() { return false; }
    }

    // Barebones Adapters for Wrapper parents
    private static class HttpServletRequestAdapter extends javax.servlet.http.HttpServletRequestWrapper {
        public HttpServletRequestAdapter() {
            super(new Request(null, null));
        }

        private static class Request implements HttpServletRequest {
            public Request(Object o, Object o1) {
            }

            @Override
            public String getAuthType() {
                return "";
            }

            @Override
            public Cookie[] getCookies() {
                return new Cookie[0];
            }

            @Override
            public long getDateHeader(String s) {
                return 0;
            }

            @Override
            public String getHeader(String s) {
                return "";
            }

            @Override
            public Enumeration<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                return null;
            }

            @Override
            public int getIntHeader(String s) {
                return 0;
            }

            @Override
            public String getMethod() {
                return "";
            }

            @Override
            public String getPathInfo() {
                return "";
            }

            @Override
            public String getPathTranslated() {
                return "";
            }

            @Override
            public String getContextPath() {
                return "";
            }

            @Override
            public String getQueryString() {
                return "";
            }

            @Override
            public String getRemoteUser() {
                return "";
            }

            @Override
            public boolean isUserInRole(String s) {
                return false;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public String getRequestedSessionId() {
                return "";
            }

            @Override
            public String getRequestURI() {
                return "";
            }

            @Override
            public StringBuffer getRequestURL() {
                return null;
            }

            @Override
            public String getServletPath() {
                return "";
            }

            @Override
            public HttpSession getSession(boolean b) {
                return null;
            }

            @Override
            public HttpSession getSession() {
                return null;
            }

            @Override
            public String changeSessionId() {
                return "";
            }

            @Override
            public boolean isRequestedSessionIdValid() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                return false;
            }

            @Override
            public boolean isRequestedSessionIdFromUrl() {
                return false;
            }

            @Override
            public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
                return false;
            }

            @Override
            public void login(String s, String s1) throws ServletException {

            }

            @Override
            public void logout() throws ServletException {

            }

            @Override
            public Collection<Part> getParts() throws IOException, ServletException {
                return List.of();
            }

            @Override
            public Part getPart(String s) throws IOException, ServletException {
                return null;
            }

            @Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
                return null;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return "";
            }

            @Override
            public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

            }

            @Override
            public int getContentLength() {
                return 0;
            }

            @Override
            public long getContentLengthLong() {
                return 0;
            }

            @Override
            public String getContentType() {
                return "";
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public String getParameter(String s) {
                return "";
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return null;
            }

            @Override
            public String[] getParameterValues(String s) {
                return new String[0];
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return Map.of();
            }

            @Override
            public String getProtocol() {
                return "";
            }

            @Override
            public String getScheme() {
                return "";
            }

            @Override
            public String getServerName() {
                return "";
            }

            @Override
            public int getServerPort() {
                return 0;
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return null;
            }

            @Override
            public String getRemoteAddr() {
                return "";
            }

            @Override
            public String getRemoteHost() {
                return "";
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return null;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }

            @Override
            public String getRealPath(String s) {
                return "";
            }

            @Override
            public int getRemotePort() {
                return 0;
            }

            @Override
            public String getLocalName() {
                return "";
            }

            @Override
            public String getLocalAddr() {
                return "";
            }

            @Override
            public int getLocalPort() {
                return 0;
            }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return null;
            }

            @Override
            public boolean isAsyncStarted() {
                return false;
            }

            @Override
            public boolean isAsyncSupported() {
                return false;
            }

            @Override
            public AsyncContext getAsyncContext() {
                return null;
            }

            @Override
            public DispatcherType getDispatcherType() {
                return null;
            }
        }
    }

    private static class HttpServletResponseAdapter extends javax.servlet.http.HttpServletResponseWrapper {
        public HttpServletResponseAdapter() {
            super(new Response(null, null));
        }

        private static class Response implements HttpServletResponse {
            public Response(Object o, Object o1) {
            }

            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String s) {
                return false;
            }

            @Override
            public String encodeURL(String s) {
                return "";
            }

            @Override
            public String encodeRedirectURL(String s) {
                return "";
            }

            @Override
            public String encodeUrl(String s) {
                return "";
            }

            @Override
            public String encodeRedirectUrl(String s) {
                return "";
            }

            @Override
            public void sendError(int i, String s) throws IOException {

            }

            @Override
            public void sendError(int i) throws IOException {

            }

            @Override
            public void sendRedirect(String s) throws IOException {

            }

            @Override
            public void setDateHeader(String s, long l) {

            }

            @Override
            public void addDateHeader(String s, long l) {

            }

            @Override
            public void setHeader(String s, String s1) {

            }

            @Override
            public void addHeader(String s, String s1) {

            }

            @Override
            public void setIntHeader(String s, int i) {

            }

            @Override
            public void addIntHeader(String s, int i) {

            }

            @Override
            public void setStatus(int i) {

            }

            @Override
            public void setStatus(int i, String s) {

            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public String getHeader(String s) {
                return "";
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return List.of();
            }

            @Override
            public Collection<String> getHeaderNames() {
                return List.of();
            }

            @Override
            public String getCharacterEncoding() {
                return "";
            }

            @Override
            public String getContentType() {
                return "";
            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return null;
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) {

            }

            @Override
            public void setContentLength(int i) {

            }

            @Override
            public void setContentLengthLong(long l) {

            }

            @Override
            public void setContentType(String s) {

            }

            @Override
            public void setBufferSize(int i) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void flushBuffer() throws IOException {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public void setLocale(Locale locale) {

            }

            @Override
            public Locale getLocale() {
                return null;
            }
        }
    }
}
