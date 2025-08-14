package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private Connection connection;

    public ItemDAOImpl() {
        this.connection = connection;
    }
    @Override
    public boolean addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (name, description, price, stock_quantity, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getStockQuantity());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET name=?, description=?, price=?, stock_quantity=?, updated_at=NOW() WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getStockQuantity());
            ps.setInt(5, item.getId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                items.add(new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                ));
            }
        }
        return items;
    }

    public List<Item> searchItems(String term) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE name LIKE ? ORDER BY name LIMIT 10";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + term + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setStockQuantity(rs.getInt("stock_quantity"));
                items.add(item);
            }
        }
        return items;
    }

}
