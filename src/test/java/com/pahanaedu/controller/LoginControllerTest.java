package com.pahanaedu.controller;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

public class LoginControllerTest {

    private LoginController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        // Create controller instance
        controller = new LoginController() {

            protected UserService getUserService() {
                return new UserService() {
                    @Override
                    public User authenticate(UserDTO userDTO) {
                        // Only admin user for testing
                        if ("admin".equals(userDTO.getUsername()) && "admin123".equals(userDTO.getPassword())) {
                            User u = new User();
                            u.setUsername("admin");
                            u.setRole("admin");
                            return u;
                        }
                        // Invalid credentials
                        return null;
                    }
                };
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.getSession(true);
    }

    @Test
    public void testSuccessfulLoginRedirectAdmin() throws ServletException, IOException {
        request.setParameter("username", "admin");
        request.setParameter("password", "admin123");

        controller.doPost(request, response);

        // Check session attribute
        assertNotNull(request.getSession(false).getAttribute("user"));
        assertEquals("admin", ((User) request.getSession(false).getAttribute("user")).getRole());

        // Check redirect URL
        assertTrue(response.getRedirectedUrl().contains("admin_dashboard.jsp"));
    }

    @Test
    public void testInvalidLoginRedirectsBackToLogin() throws ServletException, IOException {
        request.setParameter("username", "wrong");
        request.setParameter("password", "wrong123");

        // Set a mock dispatcher to avoid NPE
        request.setRequestDispatcher(new RequestDispatcher() {
            @Override
            public void forward(ServletRequest req, ServletResponse res) {
                // Mock forward: do nothing
            }

            @Override
            public void include(ServletRequest req, ServletResponse res) {
                // Mock include: do nothing
            }
        });

        controller.doPost(request, response);

        // Session should not have user
        assertNull(request.getSession(false).getAttribute("user"));

        // Error attribute should be set
        assertEquals("Invalid username or password", request.getAttribute("error"));
    }
}

