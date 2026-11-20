package org.src;


import org.src.model.*;
import org.src.service.CartService;
import org.src.service.CustomerService;
import org.src.service.OrderService;
import org.src.service.ProductService;
import org.src.util.InputUtil;

import java.util.ArrayList;

public class Main {
    private static final CustomerService customerService = new CustomerService();
    private static final ProductService productService = new ProductService();
    private static final CartService cartService = new CartService(productService);
    private static final OrderService orderService = new OrderService(productService,cartService);

    public static void main(String[] args) {
        int choice;
        System.out.println("================= Welcome To My E-Commerce Application ==================");

        do {
            displayMenu();
            choice = InputUtil.getInt();
            switch (choice) {
                case 1:
                    costumerManagement();
                        break;

                case 2:
                    do{
                        displayProductManagementMenu();
                        choice = InputUtil.getInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Enter the product name:");
                                String productName = InputUtil.getString();
                                System.out.println("Enter the product price:");
                                double productPrice = InputUtil.getDouble();
                                System.out.println("Enter the product quantity:");
                                int productQuantity = InputUtil.getInt();
                                System.out.println("Enter the product category:");
                                String productCategory = InputUtil.getString();
                                Product product =new Product(productName, productPrice, productQuantity, productCategory);
                                String added = productService.addProduct(product);
                                if(added.trim().compareToIgnoreCase("SUCCESS")==0) {
                                    System.out.println("\u001B[32m product added: successfully\u001B[0m");
                                }
                                if(added.trim().compareToIgnoreCase("PRODUCT_ALREADY_EXISTS")==0) {
                                    System.out.println("\u001B[31m product already exists\u001B[0m");
                                }
                                if (added.trim().compareToIgnoreCase("Invalid_price")==0) {
                                    System.out.println("\u001B[31m Invalid Price \u001B[0m");
                                }
                                if (added.trim().compareToIgnoreCase("INVALID_PRODUCT_QUANTITY")==0) {
                                    System.out.println("\u001B[31m INVALID_PRODUCT_QUANTITY\u001B[0m");
                                }

                                break;
                            case 2:
                                ArrayList<Product> products = productService.getAllProducts();
                                if(products.size()>0){
                                    products.forEach(product1 ->{
                                                System.out.println("Product Id : "+product1.getProductId());
                                                System.out.println("Product Name : "+product1.getProductName());
                                                System.out.println("Product Price : "+product1.getProductPrice());
                                                System.out.println("Product Quantity : "+product1.getProductQuantity());
                                                System.out.println("Product Category : "+product1.getProductCategory());
                                                System.out.println("=================================");
                                            }
                                    );
                                }else {
                                    System.out.println("\u001B[31m Product not found \u001B[0m");
                                }
                                break;
                            case 3:
                                System.out.println("Enter product ID:");
                                int productId1 = InputUtil.getInt();

                                Product findProduct = productService.getProductById(productId1);
                                if(findProduct!=null) {
                                    System.out.println("Product ID : "+findProduct.getProductId());
                                    System.out.println("Product Name : "+findProduct.getProductName());
                                    System.out.println("Product Price : "+findProduct.getProductPrice());
                                    System.out.println("Product Quantity : "+findProduct.getProductQuantity());
                                    System.out.println("Product Category : "+findProduct.getProductCategory());
                                }else {
                                    System.out.println("\u001B[31m Product not found with Id : "+productId1+"\u001B[0m");
                                }
                               break;
                            case 4:
                                System.out.println("Enter product Name you want to search:");
                                String searchProductName = InputUtil.getString();

                               Product searchedProduct = productService.searchProductByName(searchProductName);
                               if (searchedProduct != null) {
                                   System.out.println("Product ID : "+searchedProduct.getProductId());
                                   System.out.println("Product Name : "+searchedProduct.getProductName());
                                   System.out.println("Product Price : "+searchedProduct.getProductPrice());
                                   System.out.println("Product Quantity : "+searchedProduct.getProductQuantity());
                                   System.out.println("Product Category : "+searchedProduct.getProductCategory());
                               }else {
                                   System.out.println("\u001B[31m Sorry product not found! \u001B[0m");
                               }
                                break;
                            case 5:
                                System.out.println("Enter upadte product ID:");
                                int productId = InputUtil.getInt();
                                System.out.println("Enter the update product name:");
                                productName = InputUtil.getString();
                                System.out.println("Enter the update product price:");
                                productPrice = InputUtil.getDouble();
                                System.out.println("Enter the update product quantity:");
                                productQuantity = InputUtil.getInt();
                                System.out.println("Enter the product category:");
                                productCategory = InputUtil.getString();

                                Product updateProduct = new Product(productName,productPrice,productQuantity,productCategory);

                                boolean bool = productService.updateProduct(productId,updateProduct);
                                if (bool) {
                                    System.out.println("\u001B[32m Product updated successfully! \u001B[0m");
                                }else {
                                    System.out.println("\u001B[31m Sorry product not found with this Id : "+productId+"\u001B[0m");
                                }
                                break;
                            case 6:
                                System.out.println("Enter product id you want to delete:");
                                productId = InputUtil.getInt();
                                bool = productService.removeProduct(productId);
                                if(bool == true){
                                    System.out.println("product has been deleted successfully");
                                }else {
                                    System.out.println("\u001B[31m product not found with this ID"+productId+" \u001B[0m");
                                }
                                break;
                            case 7:
                                System.out.println("Enter product category you want search : ");
                                String serchProductCategory = InputUtil.getString();
                                ArrayList<Product> searchedProducts = productService.searchProductByCategory(serchProductCategory);
                                if (searchedProducts.isEmpty()) {
                                    System.out.println("\u001B[31m no matching products found with this category \u001B[0m");
                                }
                                searchedProducts.forEach(product1 ->{
                                    System.out.println("Product Id : "+product1.getProductId());
                                    System.out.println("Product Name : "+product1.getProductName());
                                    System.out.println("Product Price : "+product1.getProductPrice());
                                    System.out.println("Product Quantity : "+product1.getProductQuantity());
                                    System.out.println("Product Category : "+product1.getProductCategory());
                                    System.out.println("=============================================");
                                });
                                break;
                            case  8:
                                System.out.println("Back to main menu");
                                break;
                            default:
                                System.out.println("\u001B[31mEnter valid choice\u001B[0m");

                        }
                    }while(choice != 8);
                    break;
                case 3:
                    do {
                        displayCartManagementMenu();
                        choice = InputUtil.getInt();
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter costumer Id : ");

                                int costumerId = InputUtil.getInt();

                                Customer customer = customerService.getCustomerByCostumerId(costumerId);

                                if (customer != null) {
                                    Cart cart = cartService.getCartByCostumerId(costumerId);
                                    if (cart == null) {
                                        Cart cart1 = cartService.createCart(customer);
                                        System.out.println("Cart created successfully For Costumer Id : " + costumerId+" with cart Id : "+cart1.getCartId());
                                    } else {
                                        System.out.println("\u001B[31m Customer already have cart with Cart id ! : " + cart.getCartId() + " \u001B[0m");
                                    }
                                } else {
                                    System.out.println("\u001B[31m Customer not found! \u001B[0m");
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();

                                Customer customer =customerService.getCustomerByCostumerId(costumerId);
                                if (customer == null) {
                                    System.out.println("\u001B[31m Customer not found! \u001B[0m");
                                }else {
                                    Cart cart = cartService.getCartByCostumerId(costumerId);
                                    if (cart == null) {
                                        System.out.println("\u001B[31m Cart not found! \u001B[0m");
                                    }else {
                                        ArrayList<CartItem> items = cartService.getCartItems(costumerId);
                                        if (items.isEmpty()) {
                                            System.out.println("\u001B[31m No items found! \u001B[0m");
                                        }
                                            items.forEach(cartItem -> {
                                                Product product = cartItem.getProduct();
                                                if (product != null) {
                                                    System.out.println("\u001B[33m Cart Id : " + cart.getCartId()+"\u001B[0m");
                                                    System.out.println("\u001B[33m Costumer Id : " +costumerId+"\u001B[0m");
                                                    System.out.println("\u001B[33m Product Id : " + product.getProductId()+"\u001B[0m");
                                                    System.out.println("\u001B[33m Product Name : " + product.getProductName()+"\u001B[0m");
                                                    System.out.println("\u001B[33m Product Price : "+product.getProductPrice()+"\u001B[0m");
                                                    System.out.println("\u001B[33m Product Selected Quantity : "+cartItem.getSelectedQuantity()+"\u001B[0m");
                                                    System.out.println("\u001B[33m Product Subtotal : " +cartItem.getTotalPrice()+"\u001B[0m");
                                                    System.out.println("\u001B[33m ===============================================\u001B[0m");
                                                }
                                        });
                                        cartService.calculateCartTotal(costumerId);
                                    }
                                }
                            }
                            case 3 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                Customer customer =customerService.getCustomerByCostumerId(costumerId);
                                if (customer == null) {
                                    System.out.println("\u001B[31m Customer not found! \u001B[0m");
                                }else {
                                    Cart cart = cartService.getCartByCostumerId(costumerId);
                                    if (cart == null) {
                                        System.out.println("\u001B[31m Cart not found! \u001B[0m");
                                    }else {
                                        System.out.println("Enter Product Id : ");
                                        int productId = InputUtil.getInt();
                                        System.out.println("Enter Quantity you want to purchase : ");
                                        int quantity = InputUtil.getInt();
                                        boolean bool = cartService.addToCart(costumerId,productId, quantity);
                                        if (bool){
                                            System.out.println("\u001B[32m Item added successfully !\u001B[0m");
                                        }else {
                                            System.out.println("\u001B[31m Item not found! \u001B[0m");
                                        }
                                    }
                                }
                            }
                            case 4 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                System.out.println("Enter Product Id : ");
                                int productId = InputUtil.getInt();
                                System.out.println("Enter Quantity you want to update : ");
                                int quantity = InputUtil.getInt();

                                boolean bool = cartService.updateCartItemQuantity(costumerId,productId,quantity);
                                if (bool) {
                                    System.out.println("\u001B[32 Product Updated Successfully !\u001B[0m");
                                }else {
                                    System.out.println("\u001B[31 Product Updated Failed !\u001B[0m");
                                }
                            }

                            case 5 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                System.out.println("Enter Product Id : ");
                                int productId = InputUtil.getInt();
                               boolean bool = cartService.removeFromCart(costumerId,productId);
                               if (bool) {
                                   System.out.println("\u001B[32m product removed successfully !\u001B[0m");
                               }else {
                                   System.out.println("\u001B[31m product not found! \u001B[0m");
                               }
                            }

                            case 6 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                boolean bool = cartService.clearCart(costumerId);
                                if (bool) {
                                    System.out.println("\u001B[32m clear successfully !\u001B[0m");
                                }else {
                                    System.out.println("\u001B[31m clear failed! \u001B[0m");
                                }

                            }
                            case 7 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                double cartTotal =cartService.calculateCartTotal(costumerId);
                                if (cartTotal > 0) {
                                    System.out.println("\u001B[32m Cart Total : "+cartTotal+" \u001B[0m");
                                }
                            }
                            case 8 -> {
                                System.out.println("Back to Main Menu \n");
                            }
                            default -> System.out.println("\u001B[31mEnter valid choice\u001B[0m");

                        }
                    }while(choice != 8);

                    break;
                case 4:
                    do {
                        displayOrderManagementMenu();
                        choice = InputUtil.getInt();

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();

                                Customer customer =customerService.getCustomerByCostumerId(costumerId);

                                if (customer != null) {
                                    boolean bool = orderService.placeOrder(costumerId);
                                    if (bool) {
                                        System.out.println("\u001B[32m Order Placed successfully ! \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Order Placed failed! \u001B[0m");
                                    }
                                }else {
                                    System.out.println("\u001B[31m Customer not found! \u001B[0m");
                                }
                            }
                            case 2 -> {
                                System.out.println("Enter costumer Id : ");
                                int costumerId = InputUtil.getInt();
                                Customer customer =  customerService.getCustomerByCostumerId(costumerId);

                                ArrayList<Order> orders = orderService.getOrdersByCostumerId(costumerId);

                                if (!orders.isEmpty()) {
                                    System.out.println("\u001B[31m Order not found successfully ! \u001B[0m");
                                }

                                orders.forEach(order -> {
                                    if (order != null) {
                                        System.out.println("\u001B[33m ========== ORDER DETAILS ========== \u001B[0m");
                                        System.out.println("\u001B[33m Order ID: " + order.getOrderId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer ID: " + order.getCostumerId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer Name: " + order.getCostumerName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Date: " + order.getOrderDate()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Status: " + order.getOrderStatus()+"\u001B[0m");

                                        order.getOrderItems().forEach(orderItem -> {
                                            System.out.println("\u001B[33m Product ID: " + orderItem.getProductId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Name: " + orderItem.getProductName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Price: " + orderItem.getPriceAtPurchase()+"\u001B[0m");
                                            System.out.println("\u001B[33m Selected Quantity: " + orderItem.getSelectedQuantity()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Subtotal: " +
                                                    (orderItem.getPriceAtPurchase() * orderItem.getSelectedQuantity())+"\u001B[0m");
                                        });

                                        System.out.println("\u001B[33m Total Order Amount: " + order.getTotalAmount()+"\u001B[0m");
                                        System.out.println("\u001B[33m =================================== \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Customer not found! \u001B[0m");
                                    }
                                });
                            }
                            case 3 -> {
                                System.out.println("Enter Order Id : ");
                                int orderId = InputUtil.getInt();
                                Order order = orderService.getOrderById(orderId);
                                if (order != null) {
                                    System.out.println("\u001B[33m Order Id : "+order.getOrderId()+"\u001B[0m");
                                    System.out.println("\u001B[33m Costumer Id : "+order.getCostumerId()+"\u001B[0m");
                                    System.out.println("\u001B[33m Costumer Name : "+order.getCostumerName()+"\u001B[0m");
                                    System.out.println("\u001B[33m Order Date : "+order.getOrderDate()+"\u001B[0m");
                                    System.out.println("\u001B[33m Order status : "+order.getOrderStatus()+"\u001B[0m");
                                    order.getOrderItems().forEach(orderItem -> {
                                        System.out.println("\u001B[33m Product Id :  "+orderItem.getProductId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Name :  "+orderItem.getProductName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Price :  "+orderItem.getPriceAtPurchase()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Selected Quantity :  "+orderItem.getSelectedQuantity()+"\u001B[0m");
                                        System.out.println("------------------------------------");
                                    });
                                    System.out.println("\001B[33m Order Total Amount : "+order.getTotalAmount()+"\u001B[0m");
                                    System.out.println("===================================================");
                                }else {
                                    System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                }
                            }
                            case 4 -> {
                                System.out.println("Enter Order Status : ");
                                String Status = InputUtil.getString();

                                OrderStatus orderStatus =  OrderStatus.valueOf(Status.toUpperCase());

                                ArrayList<Order> orders = orderService.getOrdersByStatus(orderStatus);

                                if (orders != null || !orders.isEmpty()) {
                                    orders.forEach(order -> {
                                        if (order != null) {
                                            System.out.println("\u001B[33m Order Id : "+order.getOrderId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Costumer Id : "+order.getCostumerId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Costumer Name : "+order.getCostumerName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Order Date : "+order.getOrderDate()+"\u001B[0m");
                                            System.out.println("\u001B[33m Order status : "+order.getOrderStatus()+"\u001B[0m");
                                            order.getOrderItems().forEach(orderItem -> {
                                                System.out.println("\u001B[33m Product Id :  "+orderItem.getProductId()+"\u001B[0m");
                                                System.out.println("\u001B[33m Product Name :  "+orderItem.getProductName()+"\u001B[0m");
                                                System.out.println("\u001B[33m Product Price :  "+orderItem.getPriceAtPurchase()+"\u001B[0m");
                                                System.out.println("\u001B[33m Product Selected Quantity :  "+orderItem.getSelectedQuantity()+"\u001B[0m");
                                                System.out.println("------------------------------------");
                                            });
                                            System.out.println("\001B[33m Order Total Amount : "+order.getTotalAmount()+"\u001B[0m");
                                            System.out.println("===================================================");
                                        }else {
                                            System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                        }
                                    });
                                }

                            }
                            case 5 -> {
                                System.out.println("Enter Order Id : ");
                                int orderId = InputUtil.getInt();

                                Order order = orderService.cancelOrder(orderId);
                                ArrayList<OrderItem> orderItems = order.getOrderItems();
                                if (order != null  || !orderItems.isEmpty()) {
                                    System.out.println("\u001B[33m Order Id : "+order.getOrderId()+"\u001B[0m");
                                    System.out.println("\u001B[33m Costumer Id : "+order.getCostumerId()+"\u001B[0m");
                                    System.out.println("\u001B[33m Costumer Name : "+order.getCostumerName()+"\u001B[0m");
                                    System.out.println("\u001B[33m Order Date : "+order.getOrderDate()+"\u001B[0m");
                                    System.out.println("\u001B[33m Order status : "+order.getOrderStatus()+"\u001B[0m");
                                    orderItems.forEach(orderItem -> {
                                        System.out.println("\u001B[33m Product Id :  "+orderItem.getProductId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Name :  "+orderItem.getProductName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Price :  "+orderItem.getPriceAtPurchase()+"\u001B[0m");
                                        System.out.println("\u001B[33m Product Selected Quantity :  "+orderItem.getSelectedQuantity()+"\u001B[0m");
                                        System.out.println("------------------------------------");
                                    });
                                    System.out.println("\001B[33m Order Total Amount : "+order.getTotalAmount()+"\u001B[0m");
                                    System.out.println("===================================================");
                                }else {
                                    System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                }
                            }
                            case 6 -> {
                                ArrayList<Order> orders = orderService.getAllOrders();

                                if (orders.isEmpty()) {
                                    System.out.println("\u001B[31m No orders available! \u001B[0m");
                                    return;
                                }

                                orders.forEach(order -> {
                                    if (order != null) {
                                        System.out.println("\u001B[33m ========== ORDER DETAILS ========== \u001B[0m");
                                        System.out.println("\u001B[33m Order ID: " + order.getOrderId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer ID: " + order.getCostumerId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer Name: " + order.getCostumerName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Date: " + order.getOrderDate()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Status: " + order.getOrderStatus()+"\u001B[0m");

                                        order.getOrderItems().forEach(orderItem -> {
                                            System.out.println("\u001B[33m Product ID: " + orderItem.getProductId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Name: " + orderItem.getProductName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Price: " + orderItem.getPriceAtPurchase()+"\u001B[0m");
                                            System.out.println("\u001B[33m Selected Quantity: " + orderItem.getSelectedQuantity()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Subtotal: " +
                                                    (orderItem.getPriceAtPurchase() * orderItem.getSelectedQuantity())+"\u001B[0m");
                                        });
                                        System.out.println("\u001B[33m Total Order Amount: " + order.getTotalAmount()+"\u001B[0m");
                                        System.out.println("\u001B[33m =================================== \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                    }
                                });
                            }
                            case 7 -> {
                                ArrayList<Order> orders = orderService.getAllDeliveredOrders();
                                if (orders.isEmpty()) {
                                    System.out.println("\u001B[31m No orders available! \u001B[0m");
                                }

                                orders.forEach(order -> {
                                    if (order != null) {
                                        System.out.println("\u001B[33m ========== ORDER DETAILS ========== \u001B[0m");
                                        System.out.println("\u001B[33m Order ID: " + order.getOrderId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer ID: " + order.getCostumerId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer Name: " + order.getCostumerName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Date: " + order.getOrderDate()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Status: " + order.getOrderStatus()+"\u001B[0m");

                                        order.getOrderItems().forEach(orderItem -> {
                                            System.out.println("\u001B[33m Product ID: " + orderItem.getProductId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Name: " + orderItem.getProductName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Price: " + orderItem.getPriceAtPurchase()+"\u001B[0m");
                                            System.out.println("\u001B[33m Selected Quantity: " + orderItem.getSelectedQuantity()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Subtotal: " +
                                                    (orderItem.getPriceAtPurchase() * orderItem.getSelectedQuantity())+"\u001B[0m");
                                        });
                                        System.out.println("\u001B[33m Total Order Amount: " + order.getTotalAmount()+"\u001B[0m");
                                        System.out.println("\u001B[33m =================================== \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                    }
                                });

                            }
                            case 8 -> {
                                ArrayList<Order>orders =  orderService.getCancelledOrders();
                                if (orders.isEmpty()) {
                                    System.out.println("\u001B [31m No orders available!  \u001B[0m");
                                }
                                orders.forEach(order -> {
                                    if (order != null) {
                                        System.out.println("\u001B[33m ========== ORDER DETAILS ========== \u001B[0m");
                                        System.out.println("\u001B[33m Order ID: " + order.getOrderId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer ID: " + order.getCostumerId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer Name: " + order.getCostumerName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Date: " + order.getOrderDate()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Status: " + order.getOrderStatus()+"\u001B[0m");

                                        order.getOrderItems().forEach(orderItem -> {
                                            System.out.println("\u001B[33m Product ID: " + orderItem.getProductId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Name: " + orderItem.getProductName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Price: " + orderItem.getPriceAtPurchase()+"\u001B[0m");
                                            System.out.println("\u001B[33m Selected Quantity: " + orderItem.getSelectedQuantity()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Subtotal: " +
                                                    (orderItem.getPriceAtPurchase() * orderItem.getSelectedQuantity())+"\u001B[0m");
                                        });
                                        System.out.println("\u001B[33m Total Order Amount: " + order.getTotalAmount()+"\u001B[0m");
                                        System.out.println("\u001B[33m =================================== \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                    }
                                });
                            }
                            case 9 -> {
                                ArrayList<Order>orders =  orderService.getAllPendingOrders();
                                if (orders.isEmpty()) {
                                    System.out.println("\u001B [31m No orders available!  \u001B[0m");
                                }
                                orders.forEach(order -> {
                                    if (order != null) {
                                        System.out.println("\u001B[33m ========== ORDER DETAILS ========== \u001B[0m");
                                        System.out.println("\u001B[33m Order ID: " + order.getOrderId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer ID: " + order.getCostumerId()+"\u001B[0m");
                                        System.out.println("\u001B[33m Customer Name: " + order.getCostumerName()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Date: " + order.getOrderDate()+"\u001B[0m");
                                        System.out.println("\u001B[33m Order Status: " + order.getOrderStatus()+"\u001B[0m");

                                        order.getOrderItems().forEach(orderItem -> {
                                            System.out.println("\u001B[33m Product ID: " + orderItem.getProductId()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Name: " + orderItem.getProductName()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Price: " + orderItem.getPriceAtPurchase()+"\u001B[0m");
                                            System.out.println("\u001B[33m Selected Quantity: " + orderItem.getSelectedQuantity()+"\u001B[0m");
                                            System.out.println("\u001B[33m Product Subtotal: " +
                                                    (orderItem.getPriceAtPurchase() * orderItem.getSelectedQuantity())+"\u001B[0m");
                                        });
                                        System.out.println("\u001B[33m Total Order Amount: " + order.getTotalAmount()+"\u001B[0m");
                                        System.out.println("\u001B[33m =================================== \u001B[0m");
                                    }else {
                                        System.out.println("\u001B[31m Order Not found! \u001B[0m");
                                    }
                                });
                            }
                            case 10 -> {
                                double totalRevenue = orderService.getTotalRevenue();
                                System.out.println("========== TOTAL REVENUE ==========");
                                System.out.println("TotalRevenue : " + totalRevenue);
                                System.out.println("===================================");
                            }
                            case 11 -> {
                                System.out.println("=============== Back To Menu ========================\n");
                            }
                            default -> {
                                System.out.println();
                            }
                        }
                    }while (choice != 11);
                    break;
                case 5:
                    System.out.println("Thank you for using our E-Commerce Application!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1 and 5.");
            }
        }while(choice != 5);

    }
    public static void displayMenu(){
        System.out.println("1. Customer Management");
        System.out.println("2. Product Management");
        System.out.println("3. Cart Management");
        System.out.println("4. Order Management");
        System.out.println("5. Exit");
        System.out.print("Your choice : ");
    }

    public static void displayCustomerMenu(){
        System.out.println("==================  Costumer  Management ====================");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Search Customer");
        System.out.println("4. Update Customer");
        System.out.println("5. Delete Customer");
        System.out.println("6. Back to Main Menu");

        System.out.println("Enter your choice : ");

    }

    public static void costumerManagement(){
        int yourChoice;
        do {
            displayCustomerMenu();
            yourChoice = InputUtil.getInt();
            switch (yourChoice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    System.out.println("Enter Customer ID");
                    int customerId = InputUtil.getInt();
                    searchCustomerByCostumerId(customerId);
                    break;
                case 4:
                    System.out.println("Enter Customer ID : ");
                    int costumerId = InputUtil.getInt();
                    updateCustomer(costumerId);
                    break;
                case 5:
                    System.out.println("Enter Customer ID : ");
                    customerId = InputUtil.getInt();
                    deleteCustomer(customerId);
                    break;
                case 6:
                    System.out.println("Back to Main Menu \n");
                    break;
                default:
                    System.out.println("Invalid choice");

            }

        }while (yourChoice != 6) ;
    }

    public static void addCustomer(){
        System.out.println("Enter Customer Name:");
        String customerName = InputUtil.getString();
        System.out.println("Enter Customer Email:");
        String customerEmail = InputUtil.getString();
        System.out.println("Enter Customer Password:");
        String customerPassword = InputUtil.getString();

        try {
            Customer customer = new Customer(customerName, customerEmail, customerPassword);
            customerService.addCustomer(customer);


            System.out.println("\u001B[32mCustomer Created Successfully!\u001B[0m");

            System.out.println("costumer generatedId"+customer.getCostumerId());

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void viewAllCustomers(){
        ArrayList<Customer> customers = customerService.getAllCustomerList();
        if(!customers.isEmpty()){
            for(Customer customer : customers){
                System.out.println("Costumer Id : "+customer.getCostumerId());
                System.out.println("Costumer Name : "+customer.getCostumerName());
                System.out.println("Costumer Email : "+customer.getCostumerEmail());

                System.out.println("--------------------------");
            }
        }else {
            System.out.println("\u001B[31mNo customers found.\u001B[0m");
        }
    }
    public static void searchCustomerByCostumerId(int customerId){
        Customer customer = customerService.getCustomerByCostumerId(customerId);
        if(customer != null){
            System.out.println("Customer ID: " + customer.getCostumerId());
            System.out.println("Customer Name: " + customer.getCostumerName());
            System.out.println("Customer Email: " + customer.getCostumerEmail());
        }else{
            System.out.println("\u001B[31mCustomer not found!\u001B[0m");
        }

    }
    public static void updateCustomer(int costumerId){
        Customer customer = customerService.getCustomerByCostumerId(costumerId);
        if(customer == null){
            System.out.println("\u001B[31mCustomer Not Found\u001B[0m");
        }else {
            System.out.println("Enter Customer Name : ");
            String customerName = InputUtil.getString();
            System.out.println("Enter Customer Email : ");
            String costumerEmail = InputUtil.getString();
            try {
                customer.setCostumerEmail(costumerEmail);
                customer.setCostumerName(customerName);
                System.out.println("\u001B[32mCostumer Updated Successfully\u001B[0m");
            }catch(IllegalArgumentException e){
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }

        }

    }

    public static void deleteCustomer(int costumerId){
        Customer customer = customerService.getCustomerByCostumerId(costumerId);
        if(customer == null){
            System.out.println("\u001B[31mCustomer Not Found\u001B[0m");
        }else{
            customerService.deleteCustomer(costumerId);
            System.out.println("\u001B[32mCustomer Deleted Successfully\u001B[0m");
        }
    }

    public static void displayProductManagementMenu(){
        System.out.println("========== PRODUCT MENU ==========");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product by ID");
        System.out.println("4. Search Product by Name");
        System.out.println("5. Update Product");
        System.out.println("6. Remove Product");
        System.out.println("7. Search Product by Category");
        System.out.println("8. Back to Main Menu");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }

    public static void displayCartManagementMenu(){
        System.out.println("========== CART MANAGEMENT ==========");
        System.out.println("1. Create Cart");
        System.out.println("2. View My Cart");
        System.out.println("3. Add Product to Cart");
        System.out.println("4. Update Cart Item Quantity");
        System.out.println("5. Remove Product from Cart");
        System.out.println("6. Clear Cart");
        System.out.println("7. View Cart Total Amount");
        System.out.println("8. Back to Main Menu");
        System.out.print("\n Enter Your Choice: ");
    }
    public static void displayOrderManagementMenu(){
        System.out.println("========== ORDER MANAGEMENT ==========");
        System.out.println("1. Place Order");
        System.out.println("2. View My Orders");
        System.out.println("3. View Order By ID");
        System.out.println("4. View Orders By Status");
        System.out.println("5. Cancel Order");
        System.out.println("6. View All Orders");
        System.out.println("7. View Delivered Orders");
        System.out.println("8. View Cancelled Orders");
        System.out.println("9. View Pending Orders");
        System.out.println("10. View Total Revenue");
        System.out.println("11. Back to Main Menu");
        System.out.print("\n Enter Your Choice:");
    }
}

