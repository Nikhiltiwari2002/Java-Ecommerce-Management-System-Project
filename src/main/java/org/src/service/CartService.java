package org.src.service;

import org.src.model.Cart;
import org.src.model.CartItem;
import org.src.model.Customer;
import org.src.model.Product;

import java.util.ArrayList;

public class CartService {
    private final ArrayList<Cart> cartList = new ArrayList<>();
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public Cart createCart(Customer costumer) {
        if(costumer == null) {
            return null;
        }

        Cart existingCart = getCartByCostumerId(costumer.getCostumerId()) ;

        if(existingCart != null) {
            return existingCart;
        }

        Cart newCart = new Cart(costumer);

        cartList.add(newCart);

        return newCart;
    }

    public Cart getCartByCostumerId(int costumerId) {
        Cart cart = cartList.stream().filter(cart1 -> cart1.getCostumer().getCostumerId() == costumerId).findFirst().orElse(null);
        return cart;
    }

    public boolean addToCart(int customerId, int productId, int selectedQuantity) {

        Cart cart = getCartByCostumerId(customerId);
        if (cart == null || selectedQuantity <= 0) {
            return false;
        }
        Product product = productService.getProductById(productId);

        if (product == null || !productService.isProductAvailable(productId, selectedQuantity)) {
            return false;
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct() != null &&
                        item.getProduct().getProductId() == productId)
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            cart.addItems(new CartItem(product, selectedQuantity));
            return true;
        }

        int updatedQuantity = cartItem.getSelectedQuantity() + selectedQuantity;

        if (!productService.isProductAvailable(productId, updatedQuantity)) {
            return false;
        }
        cartItem.setSelectedQuantity(updatedQuantity);
        return true;
    }

    public boolean removeFromCart(int costumerId,int productId) {
        Cart cart = getCartByCostumerId(costumerId);
        if (cart == null) {
            return false;
        }

        return cart.getCartItems().removeIf(cartItem -> cartItem.getProduct() != null && cartItem.getProduct().getProductId() == productId);
    }

    public boolean updateCartItemQuantity(int costumerId, int productId, int selectedQuantity) {

        Cart cart = getCartByCostumerId(costumerId);

        if (cart == null || selectedQuantity <= 0) {
            return false;
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct() != null &&
                        item.getProduct().getProductId() == productId)
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            return false;
        }

        if (!productService.isProductAvailable(productId, selectedQuantity)) {
            return false;
        }

        return cartItem.setSelectedQuantity(selectedQuantity);
    }

    public double calculateCartTotal(int costumerId) {
        Cart cart = getCartByCostumerId(costumerId);

        if(cart == null) {
            return 0;
        }
        return  cart.getCartItems().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }


    public ArrayList<CartItem> getCartItems(int costumerId) {
        ArrayList<CartItem> cartItems = new ArrayList<>();

        Cart cart = getCartByCostumerId(costumerId);

        if(cart == null) {
            return cartItems;
        }

        cartItems.addAll(cart.getCartItems());

        return cartItems;

    }

    public boolean clearCart(int costumerId) {
        Cart cart = getCartByCostumerId(costumerId);
        if(cart == null) {
            return false;
        }
        cart.getCartItems().clear();
        return true;
    }



}
