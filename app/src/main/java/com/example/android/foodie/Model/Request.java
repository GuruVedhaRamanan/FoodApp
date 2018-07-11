package com.example.android.foodie.Model;

import java.util.List;

public class Request {

    private String RequestId;

    private String Phone;

    private String Address;

    private String Totalamount;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// 0 means placed , 1 means shipped , 2  means Delivered , 3 means received by the customer

    private List<Order> food;


    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTotalamount() {
        return Totalamount;
    }

    public void setTotalamount(String totalamount) {
        Totalamount = totalamount;
    }

    public List<Order> getFood() {
        return food;
    }

    public void setFood(List<Order> food) {
        this.food = food;
    }

    public Request() {
    }

    public Request(String requestId, String phone, String address, String totalamount, List<Order> food) {
        RequestId = requestId;
        Phone = phone;
        Address = address;
        Totalamount = totalamount;
        this.food = food;
        this.status = "0";
    }
}
