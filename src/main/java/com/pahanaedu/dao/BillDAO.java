package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import java.sql.SQLException;
import java.util.List;

public interface BillDAO {
    int createBill(Bill bill) throws SQLException;
    Bill getBillById(int billId) throws SQLException;
    List<Bill> getAllBills() throws SQLException;
    boolean updateBill(Bill bill) throws SQLException;
    boolean deleteBill(int billId) throws SQLException;
}