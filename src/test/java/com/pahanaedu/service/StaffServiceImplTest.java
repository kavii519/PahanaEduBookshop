package com.pahanaedu.service;

import com.pahanaedu.dao.impl.StaffDAOImpl;
import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.service.StaffService;
import com.pahanaedu.service.impl.StaffServiceImpl;
import com.pahanaedu.util.DBConnection;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StaffServiceImplTest {

    private static Connection connection;
    private StaffService staffService;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        connection = DBConnection.getConnection();
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException {
        if (connection != null) connection.close();
    }

    @Before
    public void setUp() {
        staffService = new StaffServiceImpl(new StaffDAOImpl(connection));
    }

    @Test
    public void testAddAndRetrieveStaff() throws SQLException {
        StaffDTO staff = new StaffDTO();
        staff.setUsername("serviceuser");
        staff.setPassword("servicepass");
        staff.setEmail("service@example.com");

        boolean added = staffService.addStaff(staff);
        assertTrue(added);

        List<StaffDTO> staffList = staffService.getAllStaff();
        boolean found = false;
        for (StaffDTO s : staffList) {
            if (s.getUsername().equals("serviceuser")) {
                found = true;
                // Clean up
                staffService.deleteStaff(s.getId());
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testUpdateStaff() throws SQLException {
        StaffDTO staff = new StaffDTO();
        staff.setUsername("updateService");
        staff.setPassword("updatepass");
        staff.setEmail("updateService@example.com");

        staffService.addStaff(staff);

        StaffDTO savedStaff = null;
        for (StaffDTO s : staffService.getAllStaff()) {
            if (s.getUsername().equals("updateService")) {
                savedStaff = s;
                break;
            }
        }
        assertNotNull(savedStaff);

        savedStaff.setUsername("updatedService");
        savedStaff.setEmail("updatedService@example.com");
        boolean updated = staffService.updateStaff(savedStaff);
        assertTrue(updated);

        StaffDTO updatedStaff = staffService.getStaffById(savedStaff.getId());
        assertEquals("updatedService", updatedStaff.getUsername());
        assertEquals("updatedService@example.com", updatedStaff.getEmail());

        // Clean up
        staffService.deleteStaff(updatedStaff.getId());
    }

    @Test
    public void testDeleteStaff() throws SQLException {
        StaffDTO staff = new StaffDTO();
        staff.setUsername("deleteService");
        staff.setPassword("deletepass");
        staff.setEmail("deleteService@example.com");
        staffService.addStaff(staff);

        StaffDTO savedStaff = null;
        for (StaffDTO s : staffService.getAllStaff()) {
            if (s.getUsername().equals("deleteService")) {
                savedStaff = s;
                break;
            }
        }
        assertNotNull(savedStaff);

        boolean deleted = staffService.deleteStaff(savedStaff.getId());
        assertTrue(deleted);

        StaffDTO deletedStaff = staffService.getStaffById(savedStaff.getId());
        assertNull(deletedStaff);
    }
}
