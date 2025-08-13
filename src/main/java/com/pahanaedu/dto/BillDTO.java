package com.pahanaedu.dto;

import java.sql.Timestamp;

public class BillDTO {
    private int billId;
    private String itemIds;
    private int customerId;
    private String customerName;
    private Timestamp billDateTime;
    private double totalAmount;

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getBillDateTime() {
        return billDateTime;
    }

    public void setBillDateTime(Timestamp billDateTime) {
        this.billDateTime = billDateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getQuantities() {
        return null;
    }
}