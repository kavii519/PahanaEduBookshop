package com.pahanaedu.dao.impl;

import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ItemDAOImplTest {

    private static Connection connection;
    private ItemDAOImpl itemDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @Before
    public void setUp() {
        itemDAO = new ItemDAOImpl();
    }

    @Test
    public void testAddItem() throws SQLException {
        Item item = new Item();
        item.setName("Test Item");
        item.setDescription("Test Description");
        item.setPrice(100.0);
        item.setStockQuantity(10);
        boolean result = itemDAO.addItem(item);
        assertTrue(result);
    }

    @Test
    public void testGetItemById() throws SQLException {
        Item item = new Item();
        item.setName("Get Item");
        item.setDescription("Get Description");
        item.setPrice(50.0);
        item.setStockQuantity(5);
        itemDAO.addItem(item);

        List<Item> allItems = itemDAO.getAllItems();
        Item lastItem = allItems.get(0); // newest item
        Item fetched = itemDAO.getItemById(lastItem.getId());
        assertNotNull(fetched);
        assertEquals("Get Item", fetched.getName());
    }

    @Test
    public void testUpdateItem() throws SQLException {
        Item item = new Item();
        item.setName("Update Item");
        item.setDescription("Old Description");
        item.setPrice(20.0);
        item.setStockQuantity(2);
        itemDAO.addItem(item);

        List<Item> allItems = itemDAO.getAllItems();
        Item lastItem = allItems.get(0);
        lastItem.setDescription("New Description");
        boolean updated = itemDAO.updateItem(lastItem);
        assertTrue(updated);
        assertEquals("New Description", itemDAO.getItemById(lastItem.getId()).getDescription());
    }

    @Test
    public void testDeleteItem() throws SQLException {
        Item item = new Item();
        item.setName("Delete Item");
        item.setDescription("Delete Description");
        item.setPrice(10.0);
        item.setStockQuantity(1);
        itemDAO.addItem(item);

        List<Item> allItems = itemDAO.getAllItems();
        Item lastItem = allItems.get(0);
        boolean deleted = itemDAO.deleteItem(lastItem.getId());
        assertTrue(deleted);
        assertNull(itemDAO.getItemById(lastItem.getId()));
    }

    @Test
    public void testSearchItems() throws SQLException {
        Item item = new Item();
        item.setName("SearchTest");
        item.setDescription("Desc");
        item.setPrice(15.0);
        item.setStockQuantity(3);
        itemDAO.addItem(item);

        List<Item> result = itemDAO.searchItems("SearchTest");
        assertFalse(result.isEmpty());
        assertEquals("SearchTest", result.get(0).getName());
    }
}
