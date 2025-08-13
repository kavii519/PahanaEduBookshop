package com.pahanaedu.service;

import com.pahanaedu.dto.BillDTO;
import java.util.List;

public interface BillService {
    int createBill(BillDTO billDTO);
    BillDTO getBillById(int billId);
    List<BillDTO> getAllBills();
    boolean updateBill(BillDTO billDTO);
    boolean deleteBill(int billId);
}