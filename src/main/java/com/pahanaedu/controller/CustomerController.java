package com.pahanaedu.controller;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.service.impl.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = {
        "/customers",
        "/customers/add",
        "/customers/edit",
        "/customers/delete"
})
public class CustomerController extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/customers/add":
                    showAddForm(request, response);
                    break;
                case "/customers/edit":
                    showEditForm(request, response);
                    break;
                case "/customers/delete":
                    deleteCustomer(request, response);
                    break;
                case "/customers":
                default:
                    listCustomers(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/customers/add":
                    addCustomer(request, response);
                    break;
                case "/customers/edit":
                    updateCustomer(request, response);
                    break;
                default:
                    listCustomers(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customers.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/add_customer.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            CustomerDTO existingCustomer = customerService.getCustomerDetails(id);
            request.setAttribute("customer", existingCustomer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_customer.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID");
            listCustomers(request, response);
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhone(request.getParameter("phone"));
        customer.setAddress(request.getParameter("address"));

        boolean isAdded = customerService.registerCustomer(customer);

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/customers");
        } else {
            request.setAttribute("error", "Failed to add customer");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add_customer.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(request.getParameter("name"));
            customer.setEmail(request.getParameter("email"));
            customer.setPhone(request.getParameter("phone"));
            customer.setAddress(request.getParameter("address"));

            boolean isUpdated = customerService.updateCustomerDetails(customer);

            if (isUpdated) {
                response.sendRedirect(request.getContextPath() + "/customers");
            } else {
                request.setAttribute("error", "Failed to update customer");
                request.setAttribute("customer", customerService.getCustomerDetails(id));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_customer.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID");
            listCustomers(request, response);
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            customerService.removeCustomer(id);
            response.sendRedirect(request.getContextPath() + "/customers");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID");
            listCustomers(request, response);
        }
    }
}