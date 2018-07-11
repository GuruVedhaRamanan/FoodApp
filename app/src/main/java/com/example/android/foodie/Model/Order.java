package com.example.android.foodie.Model;

/**
 * Created by MY PC on 29-06-2018.
 */

public class Order {

    private String ProductId;

    private String ProductName;

    private String ProductPrice;

    private String ProductQuantity;

    private String Discount;

    public Order() {
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public Order(String productId, String productName, String productPrice, String productQuantity, String discount) {
        ProductId = productId;

        ProductName = productName;

        ProductPrice = productPrice;

        ProductQuantity = productQuantity;

        Discount = discount;
    }
}
