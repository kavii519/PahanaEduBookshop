package com.pahanaedu.model;

import java.sql.Timestamp;

public class Bill {
    private int billId;
    private String itemIds;
    private String customerName;
    private int customerId;
    private Timestamp billDateTime;
    private double totalAmount;

    // Constructors
    public Bill() {}

    public Bill(String itemIds, int customerId, double totalAmount) {
        this.itemIds = itemIds;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

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

    public void setQuantities(double quantities) {
    }

    public Object getCustomerName() {
        return null;
    }

    public Customer getCustomer() {
        return null;
    }
}