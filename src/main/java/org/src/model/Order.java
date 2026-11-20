package org.src.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private static int orderIdCounter = 0;
    private int orderId;
    private int costumerId;
    private String costumerName;
    private ArrayList<OrderItem> orderItems;
    private double totalAmount;
    private OrderStatus orderStatus;
    private Date orderDate;

    public Order(Customer costumer, Date orderDate,
                 OrderStatus orderStatus, ArrayList<OrderItem> orderItems) {
        this.orderId = ++orderIdCounter;

        if (costumer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (orderDate == null) {
            throw new IllegalArgumentException("Order date cannot be null");
        }

        if (orderStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }

        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("Order items cannot be null or empty");
        }

        for (OrderItem item : orderItems) {
            if (item == null) {
                throw new IllegalArgumentException("Order item cannot be null");
            }

            if (item.getSelectedQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            if (!Double.isFinite(item.getPriceAtPurchase())
                    || item.getPriceAtPurchase() < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
        }

        this.orderId = ++orderIdCounter;
        this.costumerId = costumer.getCostumerId() ;
        this.costumerName = costumer.getCostumerName();
        this.orderDate = new Date(orderDate.getTime());
        this.orderStatus = orderStatus;
        this.orderItems = new ArrayList<>(orderItems);

        this.totalAmount = calculateTotalAmount();
    }

    public int getOrderId() {
        return orderId;
    }

    public  int getCostumerId() {
        return costumerId;
    }
    public String getCostumerName() {
        return costumerName;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Date getOrderDate() {
        return new Date(orderDate.getTime());
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    private double calculateTotalAmount() {
        double totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getSelectedQuantity()* item.getPriceAtPurchase();
        }
        return totalAmount;
    }

    public void changeOrderStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }

        this.orderStatus = newStatus;
    }
    public void displayOrderDetails() {
        System.out.println("costumer Id : " + costumerId);
        System.out.println("costumer Name : " + costumerName);
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Order Items: " + orderItems);
    }
}
