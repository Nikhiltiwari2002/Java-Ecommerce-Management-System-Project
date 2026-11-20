package org.src.service;

import org.src.model.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductService {
    private final ArrayList<Product> products = new ArrayList<>();
    public ProductService() {

    }

    public String addProduct(Product product) {
        if(getProductById(product.getProductId()) != null) {
            return "PRODUCT_ALREADY_EXISTS";
        }
        if(product.getProductPrice() <= 0){
            return "INVALID_PRICE";
        }
        if(product.getProductQuantity() <= 0){
            return "INVALID_PRODUCT_QUANTITY";
        }
        products.add(product);
        return "SUCCESS";
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productsArrayList = new ArrayList<>();

        productsArrayList.addAll(products);
        return productsArrayList;
    }

    public Product getProductById(int id) {
        Product product = products.stream().filter(product1 -> product1.getProductId() == id).findFirst().orElse(null);
        return product;
    }

    public boolean updateProduct(int updateId,Product updatedProduct) {
       Product product = getProductById(updateId);
       if(product != null) {
           product.setProductName(updatedProduct.getProductName());
           product.setProductPrice(updatedProduct.getProductPrice());
           product.setProductCategory(updatedProduct.getProductCategory());
           product.setProductQuantity(updatedProduct.getProductQuantity());
           return true;
       }else {
           return false;
       }

    }

    public boolean removeProduct(int productId) {
        return products.removeIf(product -> product.getProductId() == productId);
    }

    public Product searchProductByName(String productName) {
        return products.stream().filter(product -> product.getProductName().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }

    public ArrayList<Product> searchProductByCategory(String productCategory) {
        if(productCategory == null){
            return null;
        }
        ArrayList<Product> productsArrayList = this.products.stream()
                .filter(product -> product.getProductCategory().equalsIgnoreCase(productCategory))
                .collect(Collectors.toCollection(ArrayList::new));
        return productsArrayList;
    }

    public boolean isProductAvailable(int productId, int productQuantity) {
        if (productQuantity <= 0){
            return false;
        }
        return products.stream()
                .filter(product -> product.getProductId() == productId)
                .anyMatch(product -> product.getProductQuantity() >= productQuantity);
    }

    public boolean stockDecreaseQuantity(int productId, int productQuantity) {
        if(isProductAvailable(productId, productQuantity)) {
            products.stream().filter(product -> product.getProductId() == productId).findFirst().ifPresent(product -> {
                product.setProductQuantity(product.getProductQuantity() - productQuantity);
            });
            return true;
        }
        return false;
    }

    public boolean stockIncreaseQuantity(int productId, int productQuantity) {
        if(productQuantity <= 0) {
            return false;
        }
        Product product = products.stream().filter(product1 ->  product1.getProductId() == productId).findFirst().orElse(null);
        if(product != null) {
            product.setProductQuantity(product.getProductQuantity() + productQuantity);
            return true;
        }
        return false;
    }

    public boolean canIncreaseStock(int productId, int quantity) {
        Product product = getProductById(productId);
        if (product == null || quantity <= 0) {
            return false;
        }
        return product.getProductQuantity() <= Integer.MAX_VALUE-quantity;
    }
}
