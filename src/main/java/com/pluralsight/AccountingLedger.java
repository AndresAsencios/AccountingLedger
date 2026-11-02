package com.pluralsight;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;


public class AccountingLedger {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        displayHomeMenu(read);
    }

    public static void displayHomeMenu(Scanner read) {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("=== Accounting Ledger ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Enter your choice: ");
            choice = read.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "D" -> System.out.println("Add Deposit");
                case "P" -> System.out.println("Make Payment");
                case "L" -> displayLedgerMenu(read);
                case "X" -> System.out.println("Close, Goodbye!");
                default -> System.out.println("Invalid Input");
    }
            }
        }
    public static void displayLedgerMenu(Scanner read){
        String choice = "";

        while(!choice.equalsIgnoreCase("H")) {
            System.out.println("===+ Ledger Menu +===");
            System.out.println("A) All transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            System.out.print("Enter your choice: ");
            choice = read.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "A" -> System.out.println("All transactions");
                case "D" -> System.out.println("Deposits");
                case "P" -> System.out.println("Payments");
                case "R" -> displayReportsMenu(read);
                case "H" -> System.out.println("Return Home...");
                default -> System.out.println("Invalid Input");
            }

        }
    }
    public static void displayReportsMenu(Scanner read) {
        String choice =  "";
        while(!choice.equals("0")) {
            System.out.println("=== Reports Menu ===");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            System.out.print("Enter your choice: ");
            choice = read.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println("Month to Date");
                case "2" -> System.out.println("Previous Month");
                case "3" -> System.out.println("Year to Date");
                case "4" -> System.out.println("Previous Year");
                case "5" -> System.out.println("Search by Vendor");
                case "0" -> System.out.println("Returning to Ledger...");
                default -> System.out.println("Invalid Input");
            }
        }
//        System.out.println("=== Reports Menu ===");
    }
}

