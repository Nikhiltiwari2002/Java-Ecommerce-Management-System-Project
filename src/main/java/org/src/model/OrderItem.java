package org.src.model;

public class OrderItem {
    private final int productId;
    private final String productName;
    private final int selectedQuantity;
    private final double priceAtPurchase;

    public OrderItem(int productId, String productName,
                     int selectedQuantity, double priceAtPurchase) {

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }

        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }

        if (selectedQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (!Double.isFinite(priceAtPurchase) || priceAtPurchase < 0) {
            throw new IllegalArgumentException("Price must be finite and non-negative");
        }

        this.productId = productId;
        this.productName = productName;
        this.selectedQuantity = selectedQuantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }
}
