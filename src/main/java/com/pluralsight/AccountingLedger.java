package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class AccountingLedger {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
        loadTransactions(transactions);
        System.out.println("Loaded transactions: " + transactions.size());
        displayHomeMenu(read, transactions);
    }

    private static void displayHomeMenu(Scanner read, ArrayList<Transaction> transactions) {
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
                case "D" -> addDeposit(read, transactions);
                case "P" -> makePayment(read, transactions);
                case "L" -> displayLedgerMenu(read, transactions);
                case "X" -> System.out.println("Close, Goodbye!");
                default -> System.out.println("Invalid Input");
            }
        }
    }

    private static void displayLedgerMenu(
            Scanner read,
            ArrayList<Transaction> transactions) {
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
            // Sort transactions newest first
            transactions.sort(
                    Comparator.comparing(
                            (Transaction t) -> t.getDate() + " " + t.getTime()
                    ).reversed()
            );

            switch (choice.toUpperCase()) {
                case "A" -> {
                    System.out.println("All transactions");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                }

                case "D" -> {
                    System.out.println("=== Deposits ===");

                    for (Transaction transaction : transactions) {

                        if (transaction.getAmount() > 0) {
                            System.out.println(transaction);
                        }
                    }
                }
                case "P" -> {
                    System.out.println("=== Payments ===");

                    for (Transaction transaction : transactions) {

                        if (transaction.getAmount() < 0) {
                            System.out.println(transaction);
                        }
                    }
                }
                case "R" -> displayReportsMenu(read, transactions);
                case "H" -> System.out.println("Return Home...");
                default -> System.out.println("Invalid Input");
            }
        }
    }

    private static void displayReportsMenu(Scanner read, ArrayList<Transaction> transactions) {
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
                case "1" -> monthToDate(transactions);
                case "2" -> previousMonth(transactions);
                case "3" -> System.out.println("Year to Date");
                case "4" -> System.out.println("Previous Year");
                case "5" -> searchByVendor(read, transactions);
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
    private static void addDeposit(
            Scanner read,
            ArrayList<Transaction> transactions) {

        System.out.println("=== Add Deposit ===");

        System.out.print("Enter description: ");
        String description = read.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = read.nextLine();

        System.out.print("Enter deposit amount: ");
        String amountInput = read.nextLine();

        double amount;

        try {
            amount = Double.parseDouble(amountInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount! Please enter a number.");
            return;
        }

        if(!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Deposit must be a valid positive number!");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        Transaction transaction = new Transaction(
                date,
                time,
                description,
                vendor,
                amount
        );

        if (saveTransaction(transaction)) {
            transactions.add(transaction);
            System.out.println("Deposit saved successfully!");
        }
    }
    private static boolean saveTransaction(Transaction transaction) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("src/main/resources/transactions.csv", true))) {

            String csvLine = transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount();

            writer.write(csvLine);
            writer.newLine();

            return true;

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
            return false;
        }
    }
    private static void makePayment(
            Scanner read,
            ArrayList<Transaction> transactions) {

        System.out.println("=== Make Payment ===");

        System.out.print("Enter description: ");
        String description = read.nextLine();

        System.out.print("Enter Vendor: ");
        String vendor = read.nextLine();

        System.out.print("Enter payment amount: ");
        String amountInput = read.nextLine();

        double amount;

        try {
            amount = Double.parseDouble(amountInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount! Please enter a number.");
            return;
        }
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Amount must be a valid positive number!");
        }
        amount = -amount;

        LocalDateTime now = LocalDateTime.now();

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        Transaction transaction = new Transaction(
                date,
                time,
                description,
                vendor,
                amount
        );
        if (saveTransaction(transaction)) {
            transactions.add(transaction);
            System.out.println("Payment saved successfully!");
        }
    }
    private static void searchByVendor( Scanner read, ArrayList<Transaction> transactions) {
        System.out.println("=== Search by Vendor ===");

        System.out.print("Enter vendor name: ");
        String vendor = read.nextLine().trim();

        for (Transaction transaction : transactions) {

            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
            }
        }
        boolean found = false;

        for (Transaction transaction : transactions) {

            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for: " + vendor);
        }
    }
    private static void monthToDate(ArrayList<Transaction> transactions) {

        LocalDate today = LocalDate.now();

        System.out.println("=== Month to Date ===");
        System.out.println("Today's date: " + today);

        LocalDate startOfMonth = today.withDayOfMonth(1);
        System.out.println("Start Date: " + startOfMonth);
        System.out.println("End Date: " + today);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startOfMonth) && !transactionDate.isAfter(today)) {

                System.out.println(transaction);
            }
        }
    }
    private static void previousMonth( ArrayList<Transaction> transactions) {

        LocalDate today = LocalDate.now();

        LocalDate startOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);

        LocalDate endOfPreviousMonth = today.withDayOfMonth(1).minusDays(1);

        System.out.println("=== Previous Month ===");
        System.out.println("Start Date:" + startOfPreviousMonth);
        System.out.println("End Date: " + endOfPreviousMonth);

        for (Transaction transaction : transactions) {

            LocalDate transactionDate =
                    LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startOfPreviousMonth) && !transactionDate.isAfter(endOfPreviousMonth)) {

                System.out.println(transaction);
            }
        }
    }
}