package com.pahanaedu.service.impl;

import com.pahanaedu.dao.StaffDAO;
import com.pahanaedu.dto.StaffDTO;
import com.pahanaedu.service.StaffService;

import java.sql.SQLException;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    private StaffDAO staffDAO;

    public StaffServiceImpl(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    @Override
    public List<StaffDTO> getAllStaff() throws SQLException {
        return staffDAO.getAllStaff();
    }

    @Override
    public StaffDTO getStaffById(int id) throws SQLException {
        return staffDAO.getStaffById(id);
    }

    @Override
    public boolean addStaff(StaffDTO staff) throws SQLException {
        return staffDAO.addStaff(staff);
    }

    @Override
    public boolean updateStaff(StaffDTO staff) throws SQLException {
        return staffDAO.updateStaff(staff);
    }

    @Override
    public boolean deleteStaff(int id) throws SQLException {
        return staffDAO.deleteStaff(id);
    }
}
