package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dao.impl.CustomerDAOImpl;
import com.pahanaedu.dao.impl.ItemDAOImpl;
import com.pahanaedu.dto.BillDTO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.impl.BillServiceImpl;
import com.pahanaedu.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bill/*")
public class BillController extends HttpServlet {

    private BillService billService = new BillServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/list":
                    listBills(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                default:
                    listBills(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/insert":
                    insertBill(request, response);
                    break;
                default:
                    listBills(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listBills(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<BillDTO> listBill = billService.getAllBills();
        request.setAttribute("listBill", listBill);
        request.getRequestDispatcher("/bill-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/bill-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BillDTO existingBill = billService.getBillById(id);
        if (existingBill == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bill not found");
            return;
        }
        request.setAttribute("bill", existingBill);
        request.getRequestDispatcher("/bill-form.jsp").forward(request, response);
    }

    private void insertBill(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String itemIds = request.getParameter("itemIds");
        String customerIdStr = request.getParameter("customerId");
        String totalAmountStr = request.getParameter("totalAmount");

        // 1. Validate required fields
        if (itemIds == null || itemIds.trim().isEmpty() ||
                customerIdStr == null || customerIdStr.trim().isEmpty() ||
                totalAmountStr == null || totalAmountStr.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required.");
            showNewForm(request, response);
            return;
        }

        // 2. Parse customerId and totalAmount
        int customerId;
        double totalAmount;
        try {
            customerId = Integer.parseInt(customerIdStr.trim());
            totalAmount = Double.parseDouble(totalAmountStr.trim());
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format.");
            showNewForm(request, response);
            return;
        }

        // 3. Prepare BillDTO and create bill
        BillDTO newBill = new BillDTO();
        newBill.setItemIds(itemIds);
        newBill.setCustomerId(customerId);
        newBill.setTotalAmount(totalAmount);

        int billId = billService.createBill(newBill);

        // 4. Handle response
        if (billId != -1) {
            request.setAttribute("success", "Bill created successfully!");
            request.getRequestDispatcher("/bill-form.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to create bill");
            request.getRequestDispatcher("/bill-form.jsp").forward(request, response);
        }

    }
}
