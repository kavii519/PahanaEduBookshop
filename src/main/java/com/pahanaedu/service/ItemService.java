package com.pahanaedu.service;

import com.pahanaedu.model.Item;
import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    boolean addItem(Item item) throws SQLException;
    boolean updateItem(Item item) throws SQLException;
    boolean deleteItem(int id) throws SQLException;
    Item getItemById(int id) throws SQLException;
    List<Item> getAllItems() throws SQLException;
}
