package com.coherent.training.selenium.stanila.base.pojo;

import java.math.BigDecimal;

public class Phone {
    private String name;
    private BigDecimal price;

    public Phone(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Phone(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String priceString) {
        String priceConvert = priceString.replaceAll("[^\\d,]", "").replace(",",".");
        price = new BigDecimal(priceConvert);

    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}