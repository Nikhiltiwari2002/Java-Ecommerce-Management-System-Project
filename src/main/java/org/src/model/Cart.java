package org.src.model;

import java.util.ArrayList;
import java.util.Random;

public class Cart {
    private final int cartId;
    private final Customer costumer;
    private final ArrayList<CartItem> cartItems = new ArrayList<>();
    private static int idCounter = 0;
    public Cart(Customer costumer) {
        this.cartId = ++idCounter;
        this.costumer = costumer;
    }
    public int getCartId() {
        return cartId;
    }
    public Customer getCostumer() {
        return costumer;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItems(CartItem cartItem) {
        if (this.cartItems.stream()
                .anyMatch(item -> item.getProduct().getProductId()
                        == cartItem.getProduct().getProductId())) {

            this.cartItems.stream()
                    .filter(item -> item.getProduct().getProductId()
                            == cartItem.getProduct().getProductId())
                    .findFirst()
                    .ifPresent(item -> item.setSelectedQuantity(
                            item.getSelectedQuantity() + cartItem.getSelectedQuantity()
                    ));
            return;
        }
        if(cartItem.getSelectedQuantity() <= 0) {
            System.out.println("Selected quantity is less than or equal to 0 ");
            return;
        }

        if(cartItem.getSelectedQuantity() > cartItem.getProduct().getProductQuantity()) {
            System.out.println("out of stock");
            return;
        }
        cartItems.add(cartItem);
    }

    public void removeItem(int productId) {
        if (cartItems.stream()
                .anyMatch(p -> p.getProduct().getProductId() == productId)) {

            cartItems.removeIf(p -> p.getProduct().getProductId() == productId);

        } else {
            System.out.println("Product not found with id " + productId);
        }
    }

    public void viewCart() {
        System.out.println("Products:");
        cartItems.stream().forEach(product -> System.out.println(product.toString()));
    }

    public double calculateTotalCost() {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void isCartEmpty() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty");
        }else  {
            System.out.println("Cart is not empty");
        }
    }

    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", costumer=" + costumer +
                ", cartItems=" + cartItems +
                '}';
    }
}
