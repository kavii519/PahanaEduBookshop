package com.pahanaedu.service.impl;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.dao.impl.ItemDAOImpl;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private final ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public boolean addItem(Item item) throws SQLException {
        return itemDAO.addItem(item);
    }

    @Override
    public boolean updateItem(Item item) throws SQLException {
        return itemDAO.updateItem(item);
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return itemDAO.deleteItem(id);
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        return itemDAO.getItemById(id);
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        return itemDAO.getAllItems();
    }
}
