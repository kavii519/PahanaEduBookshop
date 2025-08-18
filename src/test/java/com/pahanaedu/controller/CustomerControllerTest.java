package com.pahanaedu.controller;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
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

public class CustomerControllerTest {

    private CustomerController controller;

    // Simple fake request/response/dispatcher implementations
    private static class FakeRequest implements HttpServletRequest {
        private final Map<String,Object> attributes = new HashMap<>();
        private final Map<String,String> parameters = new HashMap<>();
        private String servletPath;

        @Override public Object getAttribute(String name) { return attributes.get(name); }

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

        @Override public void setAttribute(String name, Object o) { attributes.put(name,o); }

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

        @Override public String getParameter(String name) { return parameters.get(name); }
        public void setParameter(String name, String value) { parameters.put(name,value); }
        @Override public String getServletPath() { return servletPath; }

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

        public void setServletPath(String path) { this.servletPath = path; }

        // Many methods unused in tests → just stub them
        @Override public RequestDispatcher getRequestDispatcher(String path) {
            return new FakeDispatcher(path,this);
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

        // --- empty stubs ---
        @Override public String getContextPath() { return "/ctx"; }

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

        @Override public Enumeration<String> getParameterNames() { return Collections.enumeration(parameters.keySet()); }

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


        @Override public String getAuthType(){throw new UnsupportedOperationException();}

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

        @Override public String getMethod(){throw new UnsupportedOperationException();}

        @Override
        public String getPathInfo() {
            return "";
        }

        @Override
        public String getPathTranslated() {
            return "";
        }
        // implement others as needed...
    }

    private static class FakeResponse implements HttpServletResponse {
        public String redirectLocation;

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

        @Override public void sendRedirect(String location) { this.redirectLocation = location; }

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

        // empty stubs
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

        @Override public void setCharacterEncoding(String charset) {}

        @Override
        public void setContentLength(int i) {

        }

        @Override
        public void setContentLengthLong(long l) {

        }
    }

    private static class FakeDispatcher implements RequestDispatcher {
        private final String path;
        private final FakeRequest request;
        public String forwardedTo;
        public FakeDispatcher(String path, FakeRequest req){this.path=path;this.request=req;}
        @Override public void forward(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res) {
            this.forwardedTo = path;
            request.setAttribute("__forwarded__", path);
        }
        @Override public void include(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res){}
    }

    // Fake CustomerService to control behavior
    private static class FakeCustomerService implements CustomerService {
        public boolean addOk=true, updateOk=true;
        public List<CustomerDTO> customers = new ArrayList<>(Arrays.asList(new CustomerDTO()));
        public boolean removed;
        @Override public List<CustomerDTO> getAllCustomers() { return customers; }
        @Override public CustomerDTO getCustomerDetails(int id) { return new CustomerDTO(); }
        @Override public boolean registerCustomer(Customer c) { return addOk; }
        @Override public boolean updateCustomerDetails(Customer c) { return updateOk; }
        @Override public boolean removeCustomer(int id) { removed=true;
            return false;
        }

        @Override
        public int getTotalCustomerCount() {
            return 0;
        }

        @Override
        public Customer getCustomerById(int customerId) {
            return null;
        }
    }

    private FakeCustomerService fakeService;

    @Before
    public void setUp() {
        controller = new CustomerController();
        // inject fake service
        fakeService = new FakeCustomerService();
        controller.customerService = fakeService;
    }

    // ========== Tests ==========

    @Test
    public void testListCustomers() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers");
        FakeResponse res = new FakeResponse();

        controller.doGet(req,res);
        assertEquals("/customers.jsp", req.getAttribute("__forwarded__"));
        assertNotNull(req.getAttribute("customers"));
    }

    @Test
    public void testShowAddForm() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/add");
        FakeResponse res = new FakeResponse();
        controller.doGet(req,res);
        assertEquals("/add_customer.jsp", req.getAttribute("__forwarded__"));
    }

    @Test
    public void testShowEditFormValidId() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/edit");
        req.setParameter("id","1");
        FakeResponse res = new FakeResponse();
        controller.doGet(req,res);
        assertEquals("/edit_customer.jsp", req.getAttribute("__forwarded__"));
        assertNotNull(req.getAttribute("customer"));
    }

    @Test
    public void testShowEditFormInvalidId() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/edit");
        req.setParameter("id","notANumber");
        FakeResponse res = new FakeResponse();
        controller.doGet(req,res);
        assertEquals("Invalid customer ID", req.getAttribute("error"));
        assertEquals("/customers.jsp", req.getAttribute("__forwarded__"));
    }

    @Test
    public void testDeleteCustomerValid() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/delete");
        req.setParameter("id","2");
        FakeResponse res = new FakeResponse();
        controller.doGet(req,res);
        assertTrue(fakeService.removed);
        assertTrue(res.redirectLocation.endsWith("/ctx/customers"));
    }

    @Test
    public void testDeleteCustomerInvalidId() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/delete");
        req.setParameter("id","bad");
        FakeResponse res = new FakeResponse();
        controller.doGet(req,res);
        assertEquals("Invalid customer ID", req.getAttribute("error"));
        assertEquals("/customers.jsp", req.getAttribute("__forwarded__"));
    }

    @Test
    public void testAddCustomerSuccess() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/add");
        req.setParameter("name","n");
        req.setParameter("email","e");
        req.setParameter("phone","p");
        req.setParameter("address","a");
        FakeResponse res = new FakeResponse();
        fakeService.addOk = true;

        controller.doPost(req,res);
        assertTrue(res.redirectLocation.endsWith("/ctx/customers"));
    }

    @Test
    public void testAddCustomerFailure() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/add");
        req.setParameter("name","n");
        req.setParameter("email","e");
        req.setParameter("phone","p");
        req.setParameter("address","a");
        FakeResponse res = new FakeResponse();
        fakeService.addOk = false;

        controller.doPost(req,res);
        assertEquals("Failed to add customer", req.getAttribute("error"));
        assertEquals("/add_customer.jsp", req.getAttribute("__forwarded__"));
    }

    @Test
    public void testUpdateCustomerSuccess() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/edit");
        req.setParameter("id","5");
        req.setParameter("name","n");
        req.setParameter("email","e");
        req.setParameter("phone","p");
        req.setParameter("address","a");
        FakeResponse res = new FakeResponse();
        fakeService.updateOk = true;

        controller.doPost(req,res);
        assertTrue(res.redirectLocation.endsWith("/ctx/customers"));
    }

    @Test
    public void testUpdateCustomerFailure() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/edit");
        req.setParameter("id","6");
        req.setParameter("name","n");
        req.setParameter("email","e");
        req.setParameter("phone","p");
        req.setParameter("address","a");
        FakeResponse res = new FakeResponse();
        fakeService.updateOk = false;

        controller.doPost(req,res);
        assertEquals("Failed to update customer", req.getAttribute("error"));
        assertEquals("/edit_customer.jsp", req.getAttribute("__forwarded__"));
    }

    @Test
    public void testUpdateCustomerInvalidId() throws Exception {
        FakeRequest req = new FakeRequest();
        req.setServletPath("/customers/edit");
        req.setParameter("id","bad");
        FakeResponse res = new FakeResponse();

        controller.doPost(req,res);
        assertEquals("Invalid customer ID", req.getAttribute("error"));
        assertEquals("/customers.jsp", req.getAttribute("__forwarded__"));
    }
}
