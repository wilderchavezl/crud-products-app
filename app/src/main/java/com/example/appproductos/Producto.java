package com.example.appproductos;

import java.io.Serializable;

public class Producto  implements Serializable {
    private int id, stock;
    private String name, brand;
    private double price;

    public Producto() {
    }

    public Producto(String name, String brand, double price, int stock) {
        this.stock = stock;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Producto(int id, String name, String brand, double price, int stock) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
