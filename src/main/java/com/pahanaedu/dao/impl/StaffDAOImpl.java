package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.StaffDAO;
import com.pahanaedu.dto.StaffDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    private Connection connection;

    public StaffDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<StaffDTO> getAllStaff() throws SQLException {
        List<StaffDTO> staffList = new ArrayList<>();
        String query = "SELECT id, username, password, role, email, created_at, updated_at FROM users WHERE role = 'staff'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                StaffDTO staff = new StaffDTO();
                staff.setId(rs.getInt("id"));
                staff.setUsername(rs.getString("username"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
                staff.setEmail(rs.getString("email"));
                staff.setCreatedAt(rs.getTimestamp("created_at"));
                staff.setUpdatedAt(rs.getTimestamp("updated_at"));
                staffList.add(staff);
            }
        }
        return staffList;
    }

    @Override
    public StaffDTO getStaffById(int id) throws SQLException {
        String query = "SELECT id, username, password, role, email, created_at, updated_at FROM users WHERE id = ? AND role = 'staff'";
        StaffDTO staff = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    staff = new StaffDTO();
                    staff.setId(rs.getInt("id"));
                    staff.setUsername(rs.getString("username"));
                    staff.setPassword(rs.getString("password"));
                    staff.setRole(rs.getString("role"));
                    staff.setEmail(rs.getString("email"));
                    staff.setCreatedAt(rs.getTimestamp("created_at"));
                    staff.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }
        return staff;
    }

    @Override
    public boolean addStaff(StaffDTO staff) throws SQLException {
        String query = "INSERT INTO users (username, password, role, email) VALUES (?, ?, 'staff', ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getUsername());
            pstmt.setString(2, staff.getPassword());
            pstmt.setString(3, staff.getEmail());

            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateStaff(StaffDTO staff) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ? AND role = 'staff'";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staff.getUsername());
            pstmt.setString(2, staff.getPassword());
            pstmt.setString(3, staff.getEmail());
            pstmt.setInt(4, staff.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteStaff(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ? AND role = 'staff'";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
