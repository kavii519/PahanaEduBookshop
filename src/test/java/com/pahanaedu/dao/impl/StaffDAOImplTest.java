package com.pahanaedu.dao.impl;

import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.util.DBConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StaffDAOImplTest {

    private static Connection connection;
    private StaffDAOImpl staffDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Before
    public void setUp() throws SQLException {
        connection.setAutoCommit(false); // start transaction
        staffDAO = new StaffDAOImpl(connection);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback(); // rollback any changes
        connection.setAutoCommit(true);
    }

    @Test
    public void testAddAndGetStaff() throws SQLException {
        String uniqueUsername = "testuser_" + System.currentTimeMillis();

        StaffDTO staff = new StaffDTO();
        staff.setUsername(uniqueUsername);
        staff.setPassword("testpass");
        staff.setEmail("test@example.com");

        boolean added = staffDAO.addStaff(staff);
        assertTrue(added);

        List<StaffDTO> staffList = staffDAO.getAllStaff();
        boolean found = false;
        for (StaffDTO s : staffList) {
            if (s.getUsername().equals(uniqueUsername)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testUpdateStaff() throws SQLException {
        String uniqueUsername = "updateuser_" + System.currentTimeMillis();

        StaffDTO staff = new StaffDTO();
        staff.setUsername(uniqueUsername);
        staff.setPassword("updatepass");
        staff.setEmail("update@example.com");
        staffDAO.addStaff(staff);

        // Retrieve newly added staff
        StaffDTO savedStaff = null;
        for (StaffDTO s : staffDAO.getAllStaff()) {
            if (s.getUsername().equals(uniqueUsername)) {
                savedStaff = s;
                break;
            }
        }
        assertNotNull(savedStaff);

        // Update
        savedStaff.setUsername(savedStaff.getUsername() + "_updated");
        savedStaff.setEmail("updated@example.com");
        boolean updated = staffDAO.updateStaff(savedStaff);
        assertTrue(updated);

        StaffDTO updatedStaff = staffDAO.getStaffById(savedStaff.getId());
        assertEquals(savedStaff.getUsername(), updatedStaff.getUsername());
        assertEquals("updated@example.com", updatedStaff.getEmail());
    }

    @Test
    public void testDeleteStaff() throws SQLException {
        String uniqueUsername = "deleteuser_" + System.currentTimeMillis();

        StaffDTO staff = new StaffDTO();
        staff.setUsername(uniqueUsername);
        staff.setPassword("deletepass");
        staff.setEmail("delete@example.com");
        staffDAO.addStaff(staff);

        StaffDTO savedStaff = null;
        for (StaffDTO s : staffDAO.getAllStaff()) {
            if (s.getUsername().equals(uniqueUsername)) {
                savedStaff = s;
                break;
            }
        }
        assertNotNull(savedStaff);

        boolean deleted = staffDAO.deleteStaff(savedStaff.getId());
        assertTrue(deleted);

        StaffDTO deletedStaff = staffDAO.getStaffById(savedStaff.getId());
        assertNull(deletedStaff);
    }
}
