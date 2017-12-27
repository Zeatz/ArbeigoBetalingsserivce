package com.example.nicolai.arbeigobetalingsserivce;

/**
 * Created by Nicolai on 27-12-2017.
 */

public class Product {

    private int id;
    private String title, shortDesc;
    private float rating;
    private double price;
    private int image;

    public Product(int id, String title, String shortDesc, float rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.shortDesc = shortDesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
