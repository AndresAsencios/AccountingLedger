package com.pluralsight;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class AccountingLedger {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
        loadTransactions(transactions);
        displayHomeMenu(read);
    }

    private static void displayHomeMenu(Scanner read) {
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

    private static void displayLedgerMenu(Scanner read) {
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
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

    private static void displayReportsMenu(Scanner read) {
        String choice = "";
        while (!choice.equals("0")) {
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

    private static void loadTransactions(ArrayList<Transaction> transactions) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/transactions.csv"))) {

            String line;
            reader.readLine(); // Skip CSV header

            while ((line = reader.readLine()) != null) {

                String[] fields = line.split("\\|");

                if (fields.length != 5) {
                    System.out.println("Skipping invalid transaction: " + line);
                    continue;
                }

                try {
                    double amount = Double.parseDouble(fields[4]);

                    Transaction transaction = new Transaction(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            amount
                    );

                    transactions.add(transaction);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount: " + fields[4]);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
}