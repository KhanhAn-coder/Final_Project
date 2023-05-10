package com.example.a51900475_51900798_finalproject.Users;

public class Users {
    private String Email, Password, Phone, Username, ShopID;

    public Users(){

    }

    public Users(String email, String password, String phone, String username, String shopID) {
        Email = email;
        Password = password;
        Phone = phone;
        Username = username;
        ShopID = shopID;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String shopID) {
        ShopID = shopID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
