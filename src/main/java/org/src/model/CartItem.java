package org.src.model;

public class CartItem {
    private final Product product;
    private int selectedQuantity;

    public CartItem(final Product product,int selectedQuantity) {
        this.product = product;
        this.selectedQuantity = selectedQuantity;
    }
    public Product getProduct() {
        return product;
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public boolean setSelectedQuantity(int selectedQuantity) {

        if (product == null || selectedQuantity <= 0 ||
                selectedQuantity > product.getProductQuantity()) {
            return false;
        }

        this.selectedQuantity = selectedQuantity;

        return true;
    }

    public boolean updateSelectedQuantity(int quantity) {

        if (quantity <= 0 || product == null) {
            return false;
        }

        int updatedQuantity = selectedQuantity + quantity;

        if (updatedQuantity > product.getProductQuantity()) {
            return false;
        }

        this.selectedQuantity = updatedQuantity;

        return true;
    }
    public double getTotalPrice() {
        return product.getProductPrice()*selectedQuantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", selectedQuantity=" + selectedQuantity +
                '}';
    }
}
