package com.example.android.foodie.Model;



public class User {


    private String UserName;

   private String UserPhoneNumber;

    private String Password;

    private String Isstaff;

    public String getIsstaff() {
        return Isstaff;
    }

    public void setIsstaff(String isstaff) {
        Isstaff = isstaff;
    }

    public User(String userName, String userPhoneNumber, String password) {
        UserName = userName;
        UserPhoneNumber = userPhoneNumber;
        Password = password;
        Isstaff = "false";
    }

    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public User() {

    }
}
