package com.example.android.foodie.Model;

/**
 * Created by MY PC on 28-06-2018.
 */

public class Food {
    private String Image;

    private String Menu_Id;

    private String Name;

    private String Contents;

    private String Price;

    private String Discount;

    public Food(String image, String menu_Id, String name, String contents, String price, String discount) {
        Image = image;
        Menu_Id = menu_Id;
        Name = name;
        Contents = contents;
        Price = price;
        Discount = discount;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMenu_Id() {
        return Menu_Id;
    }

    public void setMenu_Id(String menu_Id) {
        Menu_Id = menu_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Food() {
    }
}
