package com.coherent.training.selenium.stanila.base.pojo;

public class Phone {
    private String name;
    private double price;

    public Phone(String brand, double price) {
        this.name = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
