package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection;

    public CustomerDAOImpl() {
        this.connection = connection;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email, phone, address, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getAddress());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers ORDER BY created_at DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                customer.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = ?, email = ?, phone = ?, address = ?, updated_at = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getAddress());
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(6, customer.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customers";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int createCustomer(Customer customer) {
        return 0;
    }

    @Override
    public void deleteCustomerByName(String test) {

    }

    public List<Customer> searchCustomers(String term) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE name LIKE ? OR email LIKE ? ORDER BY name LIMIT 10";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String likeTerm = "%" + term + "%";
            ps.setString(1, likeTerm);
            ps.setString(2, likeTerm);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                c.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                customers.add(c);
            }
        }
        return customers;
    }

}