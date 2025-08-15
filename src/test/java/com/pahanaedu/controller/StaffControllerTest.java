package com.pahanaedu.controller;

import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.service.StaffService;
import com.pahanaedu.service.impl.StaffServiceImpl;
import org.junit.*;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// ===== Mock Service for testing =====
class MockStaffService extends StaffServiceImpl {
    private List<StaffDTO> staffList = new ArrayList<>();
    private int idCounter = 1;

    public MockStaffService() {
        super(null);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return new ArrayList<>(staffList);
    }

    @Override
    public StaffDTO getStaffById(int id) {
        for (StaffDTO s : staffList) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    @Override
    public boolean addStaff(StaffDTO staff) {
        staff.setId(idCounter++);
        staff.setRole("staff");
        staffList.add(staff);
        return true;
    }

    @Override
    public boolean updateStaff(StaffDTO staff) {
        for (StaffDTO s : staffList) {
            if (s.getId() == staff.getId()) {
                s.setUsername(staff.getUsername());
                s.setEmail(staff.getEmail());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteStaff(int id) {
        return staffList.removeIf(s -> s.getId() == id);
    }
}

public class StaffControllerTest {

    private StaffController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockStaffService service;

    @Before
    public void setUp() throws Exception {
        service = new MockStaffService();
        controller = new StaffController() {
            @Override
            public void init() {
                this.staffService = service;
            }
        };
        controller.init();

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testInsertStaff() throws Exception {
        request.setParameter("action", "insert");
        request.setParameter("username", "testuser");
        request.setParameter("password", "testpass");
        request.setParameter("email", "test@example.com");

        controller.doPost(request, response);

        assertEquals("StaffController", response.getRedirectedUrl());
        assertEquals(1, service.getAllStaff().size());
        assertEquals("testuser", service.getAllStaff().get(0).getUsername());
    }

    @Test
    public void testUpdateStaff() throws Exception {
        // First insert
        StaffDTO staff = new StaffDTO();
        staff.setUsername("user1");
        staff.setEmail("u1@example.com");
        service.addStaff(staff);

        // Update
        request.setParameter("action", "update");
        request.setParameter("id", String.valueOf(staff.getId()));
        request.setParameter("username", "user1updated");
        request.setParameter("email", "u1updated@example.com");

        controller.doPost(request, response);

        StaffDTO updated = service.getStaffById(staff.getId());
        assertEquals("user1updated", updated.getUsername());
        assertEquals("u1updated@example.com", updated.getEmail());
    }

    @Test
    public void testDeleteStaff() throws Exception {
        StaffDTO staff = new StaffDTO();
        staff.setUsername("todelete");
        service.addStaff(staff);

        request.setParameter("action", "delete");
        request.setParameter("id", String.valueOf(staff.getId()));

        controller.doGet(request, response);

        assertNull(service.getStaffById(staff.getId()));
    }

    @Test
    public void testListStaff() throws Exception {
        service.addStaff(new StaffDTO(0, "userA", "pass", "staff", "a@example.com", null, null));
        service.addStaff(new StaffDTO(0, "userB", "pass", "staff", "b@example.com", null, null));

        MockHttpServletRequest listRequest = new MockHttpServletRequest() {
            @Override
            public RequestDispatcher getRequestDispatcher(String path) {
                return new RequestDispatcher() {
                    @Override
                    public void forward(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res) {
                        // no-op
                    }
                    @Override
                    public void include(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res) {}
                };
            }
        };

        controller.doGet(listRequest, response);

        List<StaffDTO> list = service.getAllStaff();
        assertEquals(2, list.size());
    }
}
