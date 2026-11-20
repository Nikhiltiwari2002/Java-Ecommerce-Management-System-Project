package org.src.model;

class Electronics extends Product{
    private  String brand;
    private  String warrantyPeriod;

    Electronics(String productName,double productPrice,int productQuantity,String productCategory,String brand, String warrantyPeriod) {
        super(productName,productPrice,productQuantity,productCategory);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public void display(){
        super.productDetail();
        System.out.println("Brand: " + this.brand);
        System.out.println("Warranty Period: " + this.warrantyPeriod);
    }

}