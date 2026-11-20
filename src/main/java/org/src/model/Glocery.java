package org.src.model;

public class Glocery extends Product {
    String expiryDate;

    public Glocery(String productName, double productPrice, int productQuantity, String productCategory, String expiryDate) {
        super(productName, productPrice, productQuantity, productCategory);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
