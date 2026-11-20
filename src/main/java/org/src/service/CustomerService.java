package org.src.service;

import org.src.model.Customer;

import java.util.ArrayList;

public class CustomerService {
    ArrayList<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }
    public ArrayList<Customer> getAllCustomerList() {
        ArrayList<Customer> customers= new ArrayList<>(customerList);
        return customers;
    }

    public Customer getCustomerByCostumerId(int costumerId) {
        return customerList.stream().filter(customer -> customer.getCostumerId() == costumerId).findFirst().orElse(null);
    }

    public Customer getCustomerByName(String customerName) {
        return customerList.stream().filter(customer -> customer.getCostumerName().equalsIgnoreCase(customerName)).findFirst().orElse(null);
    }

    public boolean updateCustomer(int customerId, String customerName,String customerEmail) {
        Customer customer = customerList.stream().filter(customer1 -> customer1.getCostumerId() == customerId).findFirst().orElse(null);
        if(customer == null) {
            return false;
        }

        customer.setCostumerName(customerName);
        customer.setCostumerEmail(customerEmail);
        return  true;
    }

    public boolean deleteCustomer(int customerId) {
        if (customerList.removeIf(customer -> customer.getCostumerId() == customerId))
            return true;
        return false;
    }

    public boolean isCustomerExist(int customerId) {
        boolean bool = customerList.stream().anyMatch(customer -> customer.getCostumerId() == customerId);
        return bool;
    }

    public boolean findCustomerByEmailId(String costumerEmailId) {
        Customer customer = customerList.stream().filter(customer1 -> {
            return customer1.getCostumerEmail().equals(costumerEmailId);
        }).findFirst().orElse(null);

        if(customer == null) {
            return false;
        }
        return true;
    }

    public boolean costumerValidation(String customerEmail, String customerName, String customerAddress) {
        if(customerEmail == null && customerEmail.isEmpty() && customerName == null && customerName.isEmpty() && customerAddress == null && customerAddress.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean duplicateCustomer(String customerEmail, String customerName, String customerAddress) {
        for(Customer customer : customerList) {
            if (customer.getCostumerEmail().equals(customerEmail) && customer.getCostumerName().equals(customerName)) {
                return true;
            }
        }
        return false;
    }

    public int getcountCustomer(String customerEmail) {
        return customerList.size();
    }

}
