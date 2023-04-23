package com.example.a51900475_51900798_finalproject;

public class Products {


    private int resourceID;
    private String title;
    private int price;
    private int rating;
    private int sold;

    public Products(int resourceID, String title, int price, int rating, int sold){
        this.resourceID = resourceID;
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.sold = sold;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getRating(){
        return this.rating;
    }
    public void setRating(int rating){
        this.rating = rating;
    }

    public int getSold(){
        return this.sold;
    }
    public void setSold(int sold){
        this.sold = sold;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }
}
