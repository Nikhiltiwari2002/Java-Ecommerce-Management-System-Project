package org.src.model;

public class Clothes extends Product {
    private int size;
    private String color;

    public Clothes(String productName, double productPrice, int productQuantity, String productCategory, int size, String color) {
        super(productName, productPrice, productQuantity, productCategory);
        this.size = size;
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
