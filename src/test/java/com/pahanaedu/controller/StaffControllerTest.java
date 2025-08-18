package com.pahanaedu.controller;

import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.service.StaffService;
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

public class StaffControllerTest {

    private StaffController controller;
    private StubRequest request;
    private StubResponse response;
    private StubDispatcher dispatcher;

    // Fake service implementation (in-memory)
    private static class FakeStaffService implements StaffService {
        List<StaffDTO> list = new ArrayList<>();
        StaffDTO stored;

        @Override
        public List<StaffDTO> getAllStaff() {
            return list;
        }

        @Override
        public StaffDTO getStaffById(int id) {
            StaffDTO dto = new StaffDTO();
            dto.setId(id);
            dto.setUsername("user" + id);
            dto.setEmail("email" + id + "@test.com");
            return dto;
        }

        @Override
        public boolean addStaff(StaffDTO staff) {
            this.stored = staff;
            list.add(staff);
            return false;
        }

        @Override
        public boolean updateStaff(StaffDTO staff) {
            this.stored = staff;
            return false;
        }

        @Override
        public boolean deleteStaff(int id) {
            list.removeIf(s -> s.getId() == id);
            return false;
        }
    }

    // Simple stubs
    private static class StubRequest implements HttpServletRequest {
        private Map<String, String> params = new HashMap<>();
        private Map<String, Object> attrs = new HashMap<>();
        private StubDispatcher dispatcher;

        public void setParam(String key, String value) {
            params.put(key, value);
        }

        @Override
        public String getParameter(String name) {
            return params.get(name);
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
        public Object getAttribute(String name) {
            return attrs.get(name);
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
        public void setAttribute(String name, Object o) {
            attrs.put(name, o);
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
        public RequestDispatcher getRequestDispatcher(String path) {
            dispatcher = new StubDispatcher(path);
            return dispatcher;
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

        // ----- other unused methods can throw UnsupportedOperationException ----
        @Override public String getAuthType() { return null; }

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

        @Override public String getContextPath() { return ""; }

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

        @Override public String getServletPath() { return ""; }

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
        // (all other HttpServletRequest methods can be left unimplemented or throw)
        // ...
    }

    private static class StubResponse implements HttpServletResponse {
        public String redirectTarget;

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
        public void sendRedirect(String location) throws IOException {
            this.redirectTarget = location;
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

    private static class StubDispatcher implements RequestDispatcher {
        public String path;
        public boolean forwarded = false;

        StubDispatcher(String path) {
            this.path = path;
        }

        @Override
        public void forward(javax.servlet.ServletRequest servletRequest,
                            javax.servlet.ServletResponse servletResponse) {
            forwarded = true;
        }

        @Override
        public void include(javax.servlet.ServletRequest servletRequest,
                            javax.servlet.ServletResponse servletResponse) {}
    }

    @Before
    public void setUp() {
        controller = new StaffController();
        controller.staffService = new FakeStaffService(); // inject fake
        request = new StubRequest();
        response = new StubResponse();
        dispatcher = null;
    }

    @Test
    public void testListStaff() throws Exception {
        request.setParam("action", "");
        controller.doGet(request, response);
        assertNotNull(request.getAttribute("staffList"));
        assertTrue(((List<?>) request.getAttribute("staffList")).isEmpty());
        assertTrue(request.dispatcher.forwarded);
        assertEquals("staff.jsp", request.dispatcher.path);
    }

    @Test
    public void testShowNewForm() throws Exception {
        request.setParam("action", "new");
        controller.doGet(request, response);
        assertTrue(request.dispatcher.forwarded);
        assertEquals("staff-form.jsp", request.dispatcher.path);
    }

    @Test
    public void testShowEditForm() throws Exception {
        request.setParam("action", "edit");
        request.setParam("id", "5");
        controller.doGet(request, response);
        assertNotNull(request.getAttribute("staff"));
        assertEquals("staff-form.jsp", request.dispatcher.path);
    }

    @Test
    public void testDeleteStaff() throws Exception {
        FakeStaffService svc = (FakeStaffService) controller.staffService;
        StaffDTO dto = new StaffDTO();
        dto.setId(1);
        svc.list.add(dto);

        request.setParam("action", "delete");
        request.setParam("id", "1");
        controller.doGet(request, response);

        assertEquals("StaffController", response.redirectTarget);
        assertTrue(svc.list.isEmpty());
    }

    @Test
    public void testInsertStaff() throws Exception {
        request.setParam("action", "insert");
        request.setParam("username", "john");
        request.setParam("password", "pass");
        request.setParam("email", "john@test.com");
        controller.doPost(request, response);

        FakeStaffService svc = (FakeStaffService) controller.staffService;
        assertNotNull(svc.stored);
        assertEquals("john", svc.stored.getUsername());
        assertEquals("StaffController", response.redirectTarget);
    }

    @Test
    public void testUpdateStaff() throws Exception {
        request.setParam("action", "update");
        request.setParam("id", "2");
        request.setParam("username", "updated");
        request.setParam("email", "upd@test.com");

        controller.doPost(request, response);

        FakeStaffService svc = (FakeStaffService) controller.staffService;
        assertNotNull(svc.stored);
        assertEquals("updated", svc.stored.getUsername());
        assertEquals("StaffController", response.redirectTarget);
    }

    @Test
    public void testDefaultPostFallsToList() throws Exception {
        request.setParam("action", "unknown");
        controller.doPost(request, response);
        assertTrue(request.dispatcher.forwarded);
        assertEquals("staff.jsp", request.dispatcher.path);
    }

    @Test
    public void testDefaultGetFallsToList() throws Exception {
        request.setParam("action", "unknown");
        controller.doGet(request, response);
        assertTrue(request.dispatcher.forwarded);
        assertEquals("staff.jsp", request.dispatcher.path);
    }
}
