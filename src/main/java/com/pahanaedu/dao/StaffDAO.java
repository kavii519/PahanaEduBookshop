package com.pahanaedu.dao;

import com.pahanaedu.dto.StaffDTO;

import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {
    List<StaffDTO> getAllStaff() throws SQLException;
    StaffDTO getStaffById(int id) throws SQLException;
    boolean addStaff(StaffDTO staff) throws SQLException;
    boolean updateStaff(StaffDTO staff) throws SQLException;
    boolean deleteStaff(int id) throws SQLException;
}
