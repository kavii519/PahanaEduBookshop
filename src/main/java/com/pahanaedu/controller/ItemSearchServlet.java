package com.pahanaedu.controller;

import com.pahanaedu.dao.impl.ItemDAOImpl;
import com.pahanaedu.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item/search")
public class ItemSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String term = request.getParameter("term");
        try {
            List<Item> items = new ItemDAOImpl().searchItems(term);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("[");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                response.getWriter().print(String.format(
                        "{\"id\":%d,\"name\":\"%s\",\"price\":%.2f,\"stockQuantity\":%d}",
                        item.getId(), item.getName(), item.getPrice(), item.getStockQuantity()));
                if (i < items.size() - 1) {
                    response.getWriter().print(",");
                }
            }
            response.getWriter().print("]");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
