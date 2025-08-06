package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/items")
public class ItemController extends HttpServlet {
    private final ItemService itemService = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                request.setAttribute("items", itemService.getAllItems());
                request.getRequestDispatcher("items.jsp").forward(request, response);
            } else {
                switch (action) {
                    case "new":
                        request.getRequestDispatcher("add_item.jsp").forward(request, response);
                        break;
                    case "edit":
                        int id = Integer.parseInt(request.getParameter("id"));
                        Item existingItem = itemService.getItemById(id);
                        request.setAttribute("item", existingItem);
                        request.getRequestDispatcher("edit_item.jsp").forward(request, response);
                        break;
                    case "delete":
                        itemService.deleteItem(Integer.parseInt(request.getParameter("id")));
                        response.sendRedirect("items");
                        break;
                    default:
                        response.sendRedirect("items");
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("insert".equals(action)) {
                Item newItem = new Item();
                newItem.setName(request.getParameter("name"));
                newItem.setDescription(request.getParameter("description"));
                newItem.setPrice(Double.parseDouble(request.getParameter("price")));
                newItem.setStockQuantity(Integer.parseInt(request.getParameter("stock_quantity")));
                itemService.addItem(newItem);
                response.sendRedirect("items");
            } else if ("update".equals(action)) {
                Item updateItem = new Item();
                updateItem.setId(Integer.parseInt(request.getParameter("id")));
                updateItem.setName(request.getParameter("name"));
                updateItem.setDescription(request.getParameter("description"));
                updateItem.setPrice(Double.parseDouble(request.getParameter("price")));
                updateItem.setStockQuantity(Integer.parseInt(request.getParameter("stock_quantity")));
                itemService.updateItem(updateItem);
                response.sendRedirect("items");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
