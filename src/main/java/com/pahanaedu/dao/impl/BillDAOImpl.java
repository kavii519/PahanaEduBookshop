package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;
import com.pahanaedu.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public int createBill(Bill bill) throws SQLException {
        String sql = "INSERT INTO bill (item_ids, customer_id, total_amount) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bill.getItemIds());
            stmt.setInt(2, bill.getCustomerId());
            stmt.setDouble(3, bill.getTotalAmount());

            int affectedRows;
            try {
                affectedRows = stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }

            if (affectedRows == 0) {
                throw new SQLException("Creating bill failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public Bill getBillById(int billId) throws SQLException {
        String sql = "SELECT * FROM bill WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setItemIds(rs.getString("item_ids"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setBillDateTime(rs.getTimestamp("bill_date_time"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                return bill;
            }
        }
        return null;
    }

    @Override
    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bill";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setItemIds(rs.getString("item_ids"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setBillDateTime(rs.getTimestamp("bill_date_time"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bills.add(bill);
            }
        }
        return bills;
    }

    @Override
    public boolean updateBill(Bill bill) throws SQLException {
        String sql = "UPDATE bill SET item_ids = ?, customer_id = ?, total_amount = ? WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bill.getItemIds());
            stmt.setInt(2, bill.getCustomerId());
            stmt.setDouble(3, bill.getTotalAmount());
            stmt.setInt(4, bill.getBillId());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteBill(int billId) throws SQLException {
        String sql = "DELETE FROM bill WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            return stmt.executeUpdate() > 0;
        }
    }
}
