package com.pahanaedu.controller;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

public class LogoutControllerTest {

    @Test
    public void testLogoutInvalidatesSessionAndRedirects() throws ServletException, IOException {
        LogoutController controller = new LogoutController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        controller.doGet(request, response);

        assertTrue(session.isInvalidated());
        assertEquals("/login.jsp", response.getRedirectedUrl());
    }

    @Test
    public void testLogoutWithoutSessionRedirects() throws ServletException, IOException {
        LogoutController controller = new LogoutController();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        controller.doGet(request, response);

        assertEquals("/login.jsp", response.getRedirectedUrl());
    }
}
