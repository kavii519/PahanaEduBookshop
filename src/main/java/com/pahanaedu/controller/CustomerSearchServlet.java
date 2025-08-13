package com.pahanaedu.controller;

import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customer/search")
public class CustomerSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");
        if (term == null) term = "";
        try {
            List<Customer> customers = new CustomerDAOImpl().searchCustomers(term);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("[");
            for (int i = 0; i < customers.size(); i++) {
                Customer c = customers.get(i);
                response.getWriter().print(String.format(
                        "{\"id\":%d,\"name\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\",\"address\":\"%s\"}",
                        c.getId(), c.getName(), c.getEmail(), c.getPhone(), c.getAddress()));
                if (i < customers.size() - 1) {
                    response.getWriter().print(",");
                }
            }
            response.getWriter().print("]");
        } catch (SQLException e) {
            e.printStackTrace(); // This helps you see the real error on server logs
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
