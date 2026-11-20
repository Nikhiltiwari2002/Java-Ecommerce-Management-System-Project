package org.src.model;

public class Product {
    private static int idCounter;
    private final int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productCategory;

    public Product(String productName, double productPrice, int productQuantity, String productCategory) {
        this.productId = ++idCounter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void productDetail() {
        System.out.println("===========Product Detail==========");
        System.out.println("Product Id: " + this.productId);
        System.out.println("Product Name: " + this.productName);
        System.out.println("Product Price: " + this.productPrice);
        System.out.println("Product Quantity: " + this.productQuantity);
        System.out.println("Product Category: " + this.productCategory);
    }

    public boolean isAvailable() {
        if (this.productQuantity > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
