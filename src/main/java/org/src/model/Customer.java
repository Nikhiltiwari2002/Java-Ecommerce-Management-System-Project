package org.src.model;
import java.util.Random;

public class Customer {
    private final  int costumerId;
    private String costumerName;
    private String costumerEmail;
    private String costumerPassword;
    private static int idCounter = 0;


    public Customer(String costumerName, String costumerEmail, String costumerPassword) {
        this.costumerId = ++idCounter;
        if (costumerName == null || costumerName.isBlank()) {
           throw new IllegalArgumentException("costumerName cannot be blank");
        }
        this.costumerName = costumerName;

        if ((costumerEmail == null || costumerEmail.isBlank()) || !costumerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("costumerEmail mismatch");
        }
        this.costumerEmail = costumerEmail;
        if ((costumerPassword == null || costumerPassword.isBlank()) || !costumerPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            throw new IllegalArgumentException("costumerPassword not in given format");
        }
        this.costumerPassword = costumerPassword;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        if (costumerName == null || costumerName.isBlank()) {
            throw new IllegalArgumentException("costumerName cannot be blank");
        }
        this.costumerName = costumerName;
    }

    public String getCostumerEmail() {
        return costumerEmail;
    }
    public void setCostumerEmail(String costumerEmail) {
        if ((costumerEmail == null || costumerEmail.isBlank()) || !costumerEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("costumerEmail mismatch");
        }
        this.costumerEmail = costumerEmail;
    }
    public String getCostumerPassword() {
        return costumerPassword;
    }

    public void setCostumerPassword(String costumerPassword) {
        this.costumerPassword = costumerPassword;
    }

    public void costumerDetails() {
        System.out.println("Costumer Id: " + costumerId);
        System.out.println("Costumer Name: " + costumerName);
        System.out.println("Costumer Email: " + costumerEmail);
    }
    @Override
    public String toString() {
        return "Costumer{" +
                "costumerPassword='" + costumerPassword + '\'' +
                ", costumerId=" + costumerId +
                ", costumerName='" + costumerName + '\'' +
                ", costumerEmail='" + costumerEmail +
                '}';
    }
}
