package com.pahanaedu.controller;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;

public class BillControllerTest {

    private BillController billController;

    @Before
    public void setUp() throws ServletException {
        billController = new BillController();
        billController.init();
    }

    // ---------- Mock Classes ----------
    class MockRequest extends HttpServletRequestWrapper {
        private final java.util.Map<String, String> params = new java.util.HashMap<>();
        private String pathInfo;

        public MockRequest() {
            super(new HttpServletRequest() {
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
            });
        }

        public void setParameter(String name, String value) {
            params.put(name, value);
        }

        @Override
        public String getParameter(String name) {
            return params.get(name);
        }

        @Override
        public String getPathInfo() {
            return pathInfo;
        }

        public void setPathInfo(String pathInfo) {
            this.pathInfo = pathInfo;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return new RequestDispatcher() {
                @Override
                public void forward(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
                    // Do nothing, simulate forward
                }

                @Override
                public void include(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
                    // Do nothing
                }
            };
        }
    }

    class MockResponse extends HttpServletResponseWrapper {
        private StringWriter writer = new StringWriter();
        private int status;
        private String redirect;

        public MockResponse() {
            super(new HttpServletResponse() {
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
            });
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(writer);
        }

        public String getOutput() {
            return writer.toString();
        }

        @Override
        public void sendError(int sc, String msg) {
            this.status = sc;
        }

        @Override
        public void sendRedirect(String location) {
            this.redirect = location;
        }

        public String getRedirect() {
            return redirect;
        }

        public int getStatus() {
            return status;
        }
    }

    // ---------- Test Cases ----------

    @Test
    public void testShowNewForm() throws ServletException, IOException {
        MockRequest request = new MockRequest();
        MockResponse response = new MockResponse();

        request.setPathInfo("/new");

        billController.doGet(request, response);

        // Forward should succeed without exception
        assertNull(response.getRedirect());
    }

    @Test
    public void testInsertBillSuccess() throws ServletException, IOException {
        MockRequest request = new MockRequest();
        MockResponse response = new MockResponse();

        request.setPathInfo("/insert");
        request.setParameter("itemIds", "1,2,3");
        request.setParameter("customerId", "1"); // Ensure customer with ID 1 exists
        request.setParameter("totalAmount", "150.75");

        billController.doPost(request, response);

        // Successful creation should redirect to /bill/list
        assertNotNull(response.getRedirect());
        assertTrue(response.getRedirect().contains("/bill/list"));
    }

    @Test
    public void testInsertBillValidationFail() throws ServletException, IOException {
        MockRequest request = new MockRequest();
        MockResponse response = new MockResponse();

        request.setPathInfo("/insert");
        request.setParameter("itemIds", ""); // Missing itemIds
        request.setParameter("customerId", "1");
        request.setParameter("totalAmount", "150.75");

        billController.doPost(request, response);

        // Validation fails, so no redirect
        assertNull(response.getRedirect());
    }

    @Test
    public void testShowEditFormNotFound() throws ServletException, IOException {
        MockRequest request = new MockRequest();
        MockResponse response = new MockResponse();

        request.setPathInfo("/edit");
        request.setParameter("id", "999999"); // Non-existent bill

        billController.doGet(request, response);

        // Should return 404
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testDefaultList() throws ServletException, IOException {
        MockRequest request = new MockRequest();
        MockResponse response = new MockResponse();

        request.setPathInfo("/unknown"); // default case triggers list

        billController.doGet(request, response);

        assertNull(response.getRedirect());
    }
}
