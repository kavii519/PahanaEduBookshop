package com.pahanaedu.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

// ===== Mock HttpSession =====
class MockHttpSession implements HttpSession {
    private Map<String, Object> attributes = new HashMap<>();
    private boolean invalidated = false;

    public boolean isInvalidated() {
        return invalidated;
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void invalidate() {
        invalidated = true;
        attributes.clear();
    }

    // ===== Other methods can be empty or return null/0 =====
    @Override public long getCreationTime() { return 0; }
    @Override public String getId() { return null; }
    @Override public long getLastAccessedTime() { return 0; }
    @Override public void removeAttribute(String name) {}
    @Override public void setMaxInactiveInterval(int interval) {}
    @Override public int getMaxInactiveInterval() { return 0; }
    @Override public javax.servlet.ServletContext getServletContext() { return null; }
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

// ===== Mock HttpServletRequest =====
class MockHttpServletRequest implements HttpServletRequest {
    private MockHttpSession session;
    private Map<String, String> parameters = new HashMap<>();
    private Map<String, Object> attributes = new HashMap<>();
    private RequestDispatcher dispatcher;

    public void setSession(MockHttpSession session) {
        this.session = session;
    }

    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    public void setRequestDispatcher(RequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public HttpSession getSession() {
        if (session == null) {
            session = new MockHttpSession();
        }
        return session;
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
    public HttpSession getSession(boolean create) {
        if (session == null && create) {
            session = new MockHttpSession();
        }
        return session;
    }

    @Override
    public String getParameter(String name) {
        return parameters.get(name);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object o) {
        attributes.put(name, o);
    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
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

    // ===== Other methods can return null/0/false =====
    @Override public String getAuthType() { return null; }

    @Override
    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    @Override
    public long getDateHeader(String s) {
        return 0;
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

    @Override public String getMethod() { return null; }
    @Override public String getPathInfo() { return null; }

    @Override
    public String getPathTranslated() {
        return "";
    }

    @Override public String getProtocol() { return null; }
    @Override public String getScheme() { return null; }
    @Override public String getServerName() { return null; }
    @Override public int getServerPort() { return 0; }
    @Override public java.util.Enumeration<String> getHeaderNames() { return null; }

    @Override
    public int getIntHeader(String s) {
        return 0;
    }

    @Override public String getHeader(String name) { return null; }

    @Override
    public Enumeration<String> getHeaders(String s) {
        return null;
    }

    @Override public String getRequestURI() { return null; }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return "";
    }

    @Override public java.util.Enumeration<String> getAttributeNames() { return null; }
    @Override public java.util.Locale getLocale() { return null; }
    @Override public java.util.Enumeration<java.util.Locale> getLocales() { return null; }
    @Override public boolean isSecure() { return false; }
    @Override public javax.servlet.ServletInputStream getInputStream() { return null; }
    @Override public java.io.BufferedReader getReader() { return null; }
    @Override public String getRemoteAddr() { return null; }
    @Override public String getRemoteHost() { return null; }
    @Override public void setCharacterEncoding(String env) {}
    @Override public String getCharacterEncoding() { return null; }
    @Override public int getContentLength() { return 0; }
    @Override public long getContentLengthLong() { return 0; }
    @Override public String getContentType() { return null; }
    @Override public Enumeration<String> getParameterNames() { return null; }
    @Override public String[] getParameterValues(String name) { return new String[0]; }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Map.of();
    }
}

// ===== Mock HttpServletResponse =====
class MockHttpServletResponse implements HttpServletResponse {
    private String redirectedUrl;

    public String getRedirectedUrl() {
        return redirectedUrl;
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
    public void sendRedirect(String location) {
        redirectedUrl = location;
    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    // ===== Other methods can be empty =====
    @Override public void setContentType(String type) {}

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

    @Override public java.io.PrintWriter getWriter() { return null; }
    @Override public void setStatus(int sc) {}

    @Override
    public void setStatus(int i, String s) {

    }

    @Override public int getStatus() { return 0; }

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

    @Override public void addHeader(String name, String value) {}

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override public void setHeader(String name, String value) {}
    @Override public void setCharacterEncoding(String charset) {}

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override public String getCharacterEncoding() { return null; }

    @Override
    public String getContentType() {
        return "";
    }

    @Override public javax.servlet.ServletOutputStream getOutputStream() { return null; }
}
