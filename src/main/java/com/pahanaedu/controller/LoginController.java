package com.pahanaedu.controller;

import com.pahanaedu.dto.UserDTO;
import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO userDTO = new UserDTO(username, password);
        User authenticatedUser = userService.authenticate(userDTO);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);

            if ("admin".equalsIgnoreCase(authenticatedUser.getRole())) {
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.sendRedirect("staff_dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}