package com.jike.week.dao.po;

/**
 *
 */
public class OrderPO {
    private int orderId;
    private String orderNo;
    private int userId;
    private int orderAmount;
    private int shippingAmount;
    private int payAmount;
    private int status=1;


    public long getOrderId() {
        return orderId;
    }

    public void setId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(int shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
