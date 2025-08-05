package com.pahanaedu.controller;

import com.pahanaedu.dao.StaffDAO;
import com.pahanaedu.dao.impl.StaffDAOImpl;
import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.service.StaffService;
import com.pahanaedu.service.impl.StaffServiceImpl;
import com.pahanaedu.util.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StaffController")
public class StaffController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StaffService staffService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize DAO and Service
        StaffDAO staffDAO = null;
        try {
            staffDAO = new StaffDAOImpl(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        staffService = new StaffServiceImpl(staffDAO);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                listStaff(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteStaff(request, response);
                        break;
                    default:
                        listStaff(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                listStaff(request, response);
            } else {
                switch (action) {
                    case "insert":
                        insertStaff(request, response);
                        break;
                    case "update":
                        updateStaff(request, response);
                        break;
                    default:
                        listStaff(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<StaffDTO> staffList = staffService.getAllStaff();
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("staff-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StaffDTO existingStaff = staffService.getStaffById(id);
        request.setAttribute("staff", existingStaff);
        request.getRequestDispatcher("staff-form.jsp").forward(request, response);
    }

    private void insertStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        StaffDTO newStaff = new StaffDTO();
        newStaff.setUsername(username);
        newStaff.setPassword(password);
        newStaff.setEmail(email);

        staffService.addStaff(newStaff);
        response.sendRedirect("StaffController");
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        StaffDTO staff = new StaffDTO();
        staff.setId(id);
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setEmail(email);

        staffService.updateStaff(staff);
        response.sendRedirect("StaffController");
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffService.deleteStaff(id);
        response.sendRedirect("StaffController");
    }
}
