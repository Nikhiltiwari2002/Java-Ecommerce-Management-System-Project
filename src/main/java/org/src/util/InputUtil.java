package org.src.util;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner input = new Scanner(System.in);

    public static int getInt() {
        while (true) {
            try {
                int value = input.nextInt();
                input.nextLine();
                return value;
            }catch (InputMismatchException ex){
                System.out.println("\u001B[31m Invalid input !!! please enter a valid number.\u001B[0m");
                input.nextLine();
            }
        }
    }
    public static double getDouble() {

        while (true) {
            try {
                double value = input.nextDouble();
                input.nextLine();
                return value;
            }catch (InputMismatchException ex){
                System.out.println("\u001B[31m Invalid input !!! please enter a valid decimal number.\u001B[0m");
                input.nextLine();
            }
        }

    }

    public static String getString() {

        while (true) {
            String inputString = input.nextLine();
            if (inputString.isEmpty()) {
                System.out.println("\u001B[31m Invalid input !!! please enter a valid string \u001B[0m");
            }else {
                return inputString;
            }
        }
    }

    public static boolean getBoolean() {
        while (true) {
            String inputString = input.nextLine();
            if (inputString.equals("true")) {
                return true;
            }else if (inputString.equals("false")) {
                return false;
            }
            else  {
                System.out.println("\u001B[31m Invalid input !!! please enter a valid boolean value\u001B[0m");
            }

        }
    }

    public static Long getLong() {
        while (true) {
            try {
                Long value = input.nextLong();
                input.nextLine();
                return value;
            }catch (InputMismatchException ex){
                System.out.println("\u001B[31m Invalid input !!! please enter a valid long value \u001B[0m");
                input.nextLine();
            }
        }
    }


    public static String getString(String message) {
        while (true) {
            System.out.println(message);

            String inputString = input.nextLine();

            if (inputString.isBlank()) {
                System.out.println("\u001B[31m Invalid input! Please enter a valid string.\u001B[0m");
            } else {
                return inputString;
            }
        }
    }
}
