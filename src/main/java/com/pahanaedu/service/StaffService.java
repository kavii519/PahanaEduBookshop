package com.pahanaedu.service;

import com.pahanaedu.dto.StaffDTO;

import java.sql.SQLException;
import java.util.List;

public interface StaffService {
    List<StaffDTO> getAllStaff() throws SQLException;
    StaffDTO getStaffById(int id) throws SQLException;
    boolean addStaff(StaffDTO staff) throws SQLException;
    boolean updateStaff(StaffDTO staff) throws SQLException;
    boolean deleteStaff(int id) throws SQLException;
}