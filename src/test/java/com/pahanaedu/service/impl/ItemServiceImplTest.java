package com.pahanaedu.service.impl;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ItemServiceImplTest {

    private ItemService itemService;

    @Before
    public void setUp() {
        itemService = new ItemServiceImpl();
    }

    @Test
    public void testAddAndGetItem() throws SQLException {
        Item item = new Item();
        item.setName("Service Item");
        item.setDescription("Service Desc");
        item.setPrice(30.0);
        item.setStockQuantity(6);

        boolean added = itemService.addItem(item);
        assertTrue(added);

        List<Item> items = itemService.getAllItems();
        Item lastItem = items.get(0);
        assertEquals("Service Item", lastItem.getName());
    }

    @Test
    public void testUpdateItem() throws SQLException {
        Item item = new Item();
        item.setName("Service Update");
        item.setDescription("Old Desc");
        item.setPrice(40.0);
        item.setStockQuantity(7);
        itemService.addItem(item);

        Item lastItem = itemService.getAllItems().get(0);
        lastItem.setDescription("Updated Desc");
        boolean updated = itemService.updateItem(lastItem);
        assertTrue(updated);
        assertEquals("Updated Desc", itemService.getItemById(lastItem.getId()).getDescription());
    }

    @Test
    public void testDeleteItem() throws SQLException {
        Item item = new Item();
        item.setName("Service Delete");
        item.setDescription("Delete Desc");
        item.setPrice(25.0);
        item.setStockQuantity(4);
        itemService.addItem(item);

        Item lastItem = itemService.getAllItems().get(0);
        boolean deleted = itemService.deleteItem(lastItem.getId());
        assertTrue(deleted);
        assertNull(itemService.getItemById(lastItem.getId()));
    }
}
