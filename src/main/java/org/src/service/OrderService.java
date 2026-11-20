package org.src.service;


import org.src.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;


public class OrderService {
    private final ArrayList<Order> orderList = new ArrayList<>();
    private final ProductService productService;
    private final CartService cartService;

    public OrderService(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public boolean placeOrder(int costumerId) {

        Cart cart = cartService.getCartByCostumerId(costumerId);

        if (cart == null) {
            return false;
        }

        Customer customer = cart.getCostumer();

        if (customer == null) {
            return false;
        }

        ArrayList<CartItem> cartItems = cart.getCartItems();

        if (cartItems == null || cartItems.isEmpty()) {
            return false;
        }

        for (CartItem cartItem : cartItems) {

            if (cartItem == null) {
                return false;
            }

            Product product = cartItem.getProduct();
            int selectedQuantity = cartItem.getSelectedQuantity();

            if (product == null || selectedQuantity <= 0) {
                return false;
            }

            if (!productService.isProductAvailable(
                    product.getProductId(), selectedQuantity)) {
                return false;
            }
        }

        // Step 2: Create OrderItems
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();
            int selectedQuantity = cartItem.getSelectedQuantity();

            OrderItem orderItem = new OrderItem(
                    product.getProductId(),
                    product.getProductName(),
                    selectedQuantity,
                    product.getProductPrice()
            );

            orderItems.add(orderItem);
        }

        Order order = new Order(
                customer,
                new Date(),
                OrderStatus.PENDING,
                orderItems
        );


        ArrayList<CartItem> updatedCartItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();
            int selectedQuantity = cartItem.getSelectedQuantity();

            boolean stockUpdated = productService.stockDecreaseQuantity(
                    product.getProductId(), selectedQuantity
            );

            if (!stockUpdated) {

                boolean rollbackSuccessful = true;

                for (CartItem updatedItem : updatedCartItems) {

                    Product updatedProduct = updatedItem.getProduct();

                    boolean restored = productService.stockIncreaseQuantity(
                            updatedProduct.getProductId(),
                            updatedItem.getSelectedQuantity()
                    );

                    if (!restored) {
                        rollbackSuccessful = false;
                    }
                }

                if (!rollbackSuccessful) {
                    System.out.println("Stock rollback failed.");
                }

                return false;
            }

            updatedCartItems.add(cartItem);
        }

        orderList.add(order);
        cartItems.clear();

        return true;
    }

    public ArrayList<Order> getAllOrders() {
        return new ArrayList<>(orderList);
    }

    public Order getOrderById(int orderId) {
        return orderList.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public  ArrayList<Order> getOrdersByCostumerId(int costumerId) {
        return orderList.stream().filter(order -> order.getCostumerId() == costumerId).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean updateOrderStatus(int orderId, OrderStatus orderStatus) {

        Order order = getOrderById(orderId);

        if (order == null || orderStatus == null ||
                orderStatus == OrderStatus.CANCELLED) {
            return false;
        }

        if (isValidStatusTransition(order.getOrderStatus(), orderStatus)) {
            order.changeOrderStatus(orderStatus);
            return true;
        }

        return false;
    }

    public Order cancelOrder(int orderId) {

        Order order = getOrderById(orderId);

        if (order == null) {
            return null;
        }

        OrderStatus status = order.getOrderStatus();


        if (status != OrderStatus.PENDING &&
                status != OrderStatus.CONFIRMED) {
            return null;
        }

        ArrayList<OrderItem> items = order.getOrderItems();

        if (items == null || items.isEmpty()) {
            return null;
        }

        for (OrderItem orderItem : items) {

            if (orderItem == null) {
                return null;
            }

            int productId = orderItem.getProductId();
            int selectedQuantity = orderItem.getSelectedQuantity();

            if (selectedQuantity <= 0) {
                return null;
            }

            if (!productService.canIncreaseStock(productId, selectedQuantity)) {
                return null;
            }
        }

        boolean restorationSuccessful = true;

        ArrayList<OrderItem> restoredItems = new ArrayList<>();

        for (OrderItem orderItem : items) {

            int productId = orderItem.getProductId();
            int selectedQuantity = orderItem.getSelectedQuantity();

            boolean restored = productService.stockIncreaseQuantity(
                    productId, selectedQuantity
            );

            if (restored) {
                restoredItems.add(orderItem);
            } else {
                restorationSuccessful = false;
            }
        }

        if (!restorationSuccessful) {

            boolean rollbackSuccessful = true;

            for (OrderItem restoredItem : restoredItems) {

                boolean decreased = productService.stockDecreaseQuantity(
                        restoredItem.getProductId(),
                        restoredItem.getSelectedQuantity()
                );

                if (!decreased) {
                    rollbackSuccessful = false;
                }
            }

            if (!rollbackSuccessful) {
                System.out.println("Stock rollback failed during order cancellation.");
            }

            return null;
        }

        order.changeOrderStatus(OrderStatus.CANCELLED);

        return order;
    }

    public ArrayList<Order> getOrdersByStatus(OrderStatus status) {

        return orderList.stream().filter(order -> order.getOrderStatus()==status).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getTotalOrders(){
        return orderList.size();
    }

    public boolean isOrderExists(int orderId) {
        return getOrderById(orderId) != null;
    }

    public double getTotalRevenue() {
        return orderList.stream().filter(order ->  order.getOrderStatus()==OrderStatus.DELIVERED).mapToDouble(order ->  order.getTotalAmount()).sum();
    }

    public ArrayList<Order> getAllDeliveredOrders() {
        return orderList.stream().filter(order ->  order.getOrderStatus()==OrderStatus.DELIVERED).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Order> getCancelledOrders() {

        return orderList.stream().filter(order ->  order.getOrderStatus()==OrderStatus.CANCELLED).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Order> getAllPendingOrders() {
        return orderList.stream().filter(order ->   order.getOrderStatus()==OrderStatus.PENDING).collect(Collectors.toCollection(ArrayList::new));
    }

    public int getTotalCancelledOrders(){
        return getCancelledOrders().size();
    }

    public int getTotalPendingOrders() {
        return getAllPendingOrders().size();
    }

    public int getTotalDeliveredOrders(){
        return getAllDeliveredOrders().size();
    }

    public int getTotalShippedOrders(){
        return (int)orderList.stream().filter(order -> order.getOrderStatus()==OrderStatus.SHIPPED).count();
    }

    public int getTotalConfirmedOrders(){
        return getConfirmedOrders().size();
    }

    public ArrayList<Order> getConfirmedOrders() {
        return orderList.stream().filter(order ->  order.getOrderStatus()==OrderStatus.CONFIRMED).collect(Collectors.toCollection(ArrayList::new));

    }

    public boolean isValidStatusTransition(
            OrderStatus currentOrderStatus,
            OrderStatus newOrderStatus) {

        if (currentOrderStatus == null || newOrderStatus == null) {
            return false;
        }

        if (currentOrderStatus == OrderStatus.PENDING &&
                newOrderStatus == OrderStatus.CONFIRMED) {
            return true;
        }

        if (currentOrderStatus == OrderStatus.CONFIRMED &&
                newOrderStatus == OrderStatus.SHIPPED) {
            return true;
        }

        if (currentOrderStatus == OrderStatus.SHIPPED &&
                newOrderStatus == OrderStatus.DELIVERED) {
            return true;
        }

        return false;
    }
}
