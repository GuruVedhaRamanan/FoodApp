package com.example.android.foodie.Model;



public class Category {

 private   String Image;
    private String Name;

    public Category()
    {

    }

    public String getImage()
    {
        return Image;
    }

    public void setImage(String image)
    {
        Image = image;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public Category(String image, String name)
    {
        Image = image;
        Name = name;
    }
}
