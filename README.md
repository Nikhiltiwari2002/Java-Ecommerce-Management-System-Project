E-Commerce Console Application

Project Overview

The E-Commerce Console Application is a menu-driven application developed using Core Java. It simulates basic e-commerce operations, allowing customers to browse products, manage shopping carts, and place and manage orders.

The project focuses on implementing Object-Oriented Programming (OOP) principles, Java Collections Framework, Stream API, and modular application architecture.

Technologies Used

Programming Language: Java

Core Concepts: OOP, Encapsulation, Inheritance, Polymorphism

Collections Framework: ArrayList

Java Features: Stream API, Lambda Expressions

IDE: IntelliJ IDEA

Version Control: Git and GitHub

Application Features

1. Customer Management

Register new customers.

Customer login.

View customer details.

Search customer by ID.

Manage customer information.

2. Product Management

Add new products.

View all products.

Search products by ID.

Update product details.

Filter products by category.

Check product availability.

Increase and decrease product stock.

3. Shopping Cart Management

Create a shopping cart for customers.

Add products to the cart.

Update cart item quantities.

Remove products from the cart.

Clear shopping cart.

View cart details.

Calculate total cart amount.

Validate product availability and stock quantity.

4. Order Management

Place customer orders.

View all orders.

View customer orders.

Search orders by status.

View pending orders.

View cancelled orders.

Cancel orders.

Calculate total revenue from delivered orders.

Project Architecture

The application follows a layered architecture to separate data models, business logic, and utility operations.

Ecommerce-Console-Application/
│
├── src/
│   └── org/
│       └── src/
│           │
│           ├── model/
│           │   ├── Product.java
│           │   ├── Customer.java
│           │   ├── Cart.java
│           │   ├── CartItem.java
│           │   └── Order.java
│           │
│           ├── service/
│           │   ├── ProductService.java
│           │   ├── CustomerService.java
│           │   ├── CartService.java
│           │   └── OrderService.java
│           │
│           ├── util/
│           │   └── InputUtil.java
│           │
│           └── Main.java
│
├── screenshots/
│
├── README.md
└── .gitignore

Package Responsibilities

Model Package

Contains application data classes.

Product: Stores product information such as ID, name, price, quantity, and category.

Customer: Stores customer information.

Cart: Represents a customer's shopping cart.

CartItem: Represents a product and its selected quantity in the cart.

Order: Stores order details, items, total amount, date, and status.

Service Package

Contains the application's business logic.

ProductService: Handles product operations and stock management.

CustomerService: Handles customer-related operations.

CartService: Handles shopping cart operations and quantity validation.

OrderService: Handles order placement, cancellation, and order-related operations.

Utility Package

InputUtil: Handles user input operations.

Main Class

Main.java: Entry point of the application. Handles the main menu and connects the different service classes.

Application Workflow

Application starts from the Main class.

Customer registers or logs in.

Customer browses available products.

Customer creates a shopping cart.

Customer adds products to the cart.

Application validates product availability and stock.

Customer reviews the cart and total amount.

Customer places an order.

Application processes the order and updates product stock.

Customer can view or cancel eligible orders.

Screenshots

Screenshots of the running application will be added here.

Main Menu



Product Management



Cart Management



Order Management



How to Run the Project

Prerequisites

Java Development Kit (JDK)

IntelliJ IDEA or another Java IDE

Steps

Clone the repository:

git clone https://github.com/Nikhiltiwari2002/Ecommerce-Console-Application.git

Open the project in IntelliJ IDEA.

Configure the JDK.

Navigate to Main.java.

Run the Main class.

Follow the console menu instructions.

Key Learning Outcomes

Practical implementation of Object-Oriented Programming.

Understanding of Java Collections Framework.

Working with ArrayList and Stream API.

Using Lambda Expressions.

Implementing business logic through service classes.

Managing product inventory and shopping cart operations.

Understanding order processing and management.

Organizing Java applications using packages.

Using Git and GitHub for version control.

Future Enhancements

Integrate Oracle Database using JDBC.

Implement Spring Boot REST APIs.

Develop a React-based frontend.

Implement payment gateway integration.

Add automated unit testing using JUnit.

Implement secure customer authentication.

Author

Nikhil Tiwari

Aspiring Java Developer

Technical Skills: Core Java | JDBC | Servlets | JSP | Hibernate | Spring Boot | Oracle SQL | React

GitHub: https://github.com/Nikhiltiwari2002

This project is developed for learning and demonstrating practical Core Java development skills.