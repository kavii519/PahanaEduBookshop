package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.impl.ItemServiceImpl;
import org.junit.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.junit.Assert.*;

public class ItemControllerTest {

    private ItemController controller;

    @Before
    public void setUp() {
        controller = new ItemController();
    }

    // ===================== MOCK CLASSES =====================
    static class MockRequest extends HttpServletRequestWrapper {
        private final Map<String, String> params = new HashMap<>();
        private final Map<String, Object> attrs = new HashMap<>();
        private RequestDispatcher dispatcher;

        MockRequest() {
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
                } });
        }

        void setParameter(String key, String value) { params.put(key, value); }
        @Override
        public String getParameter(String name) { return params.get(name); }

        @Override
        public void setAttribute(String name, Object o) { attrs.put(name, o); }
        @Override
        public Object getAttribute(String name) { return attrs.get(name); }

        void setRequestDispatcher(RequestDispatcher rd) { this.dispatcher = rd; }
        @Override
        public RequestDispatcher getRequestDispatcher(String path) { return dispatcher; }
    }

    static class MockResponse extends HttpServletResponseWrapper {
        String redirect;

        MockResponse() { super(new HttpServletResponse() {
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
            }  }); }

        @Override
        public void sendRedirect(String location) { redirect = location; }

        public String getRedirect() { return redirect; }
    }

    static class MockDispatcher implements RequestDispatcher {
        boolean forwardCalled = false;
        String path;

        MockDispatcher(String path) { this.path = path; }

        @Override
        public void forward(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {
            forwardCalled = true;
        }

        @Override
        public void include(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) {}
    }

    // ===================== TESTS =====================

    @Test
    public void testDoGetDefault() throws ServletException, IOException {
        MockRequest req = new MockRequest();
        MockResponse res = new MockResponse();

        // Set a dispatcher for items.jsp
        MockDispatcher dispatcher = new MockDispatcher("items.jsp");
        req.setRequestDispatcher(dispatcher);

        controller.doGet(req, res);

        assertTrue(dispatcher.forwardCalled);
        Object itemsAttr = req.getAttribute("items");
        assertNotNull(itemsAttr);
        assertTrue(itemsAttr instanceof List<?>);
    }

    @Test
    public void testDoGetNew() throws ServletException, IOException {
        MockRequest req = new MockRequest();
        MockResponse res = new MockResponse();
        req.setParameter("action", "new");

        MockDispatcher dispatcher = new MockDispatcher("add_item.jsp");
        req.setRequestDispatcher(dispatcher);

        controller.doGet(req, res);

        assertTrue(dispatcher.forwardCalled);
    }

    @Test
    public void testDoGetDelete() throws ServletException, IOException {
        MockRequest req = new MockRequest();
        MockResponse res = new MockResponse();
        req.setParameter("action", "delete");
        req.setParameter("id", "1"); // assuming item with ID 1 exists

        controller.doGet(req, res);

        assertEquals("items", res.getRedirect());
    }

    @Test
    public void testDoPostInsert() throws ServletException, IOException {
        MockRequest req = new MockRequest();
        MockResponse res = new MockResponse();

        req.setParameter("action", "insert");
        req.setParameter("name", "JUnit Item");
        req.setParameter("description", "Test Description");
        req.setParameter("price", "12.5");
        req.setParameter("stock_quantity", "5");

        controller.doPost(req, res);

        assertEquals("items", res.getRedirect());
    }

    @Test
    public void testDoPostUpdate() throws ServletException, IOException {
        MockRequest req = new MockRequest();
        MockResponse res = new MockResponse();

        req.setParameter("action", "update");
        req.setParameter("id", "1"); // assuming item with ID 1 exists
        req.setParameter("name", "Updated Item");
        req.setParameter("description", "Updated Description");
        req.setParameter("price", "15.0");
        req.setParameter("stock_quantity", "10");

        controller.doPost(req, res);

        assertEquals("items", res.getRedirect());
    }
}
