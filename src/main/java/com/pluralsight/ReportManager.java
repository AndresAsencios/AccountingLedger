package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportManager {
    public static void displayReportsMenu(Scanner read, ArrayList<Transaction> transactions) {
        String choice = "";
        while (!choice.equals("0")) {
            System.out.println();
            System.out.println("======================================");
            System.out.println("     🌿 NURSERY FINANCIAL REPORTS");
            System.out.println("======================================");
            System.out.println("1) 📅 Month-to-Date");
            System.out.println("2) 📆 Previous Month");
            System.out.println("3) 📊 Year-to-Date");
            System.out.println("4) 📈 Previous Year");
            System.out.println("5) 🔎 Search by Customer / Vendor");
            System.out.println("0) ↩ Return to Nursery Ledger");
            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = read.nextLine().trim();

            switch (choice) {
                case "1" -> monthToDate(transactions);
                case "2" -> previousMonth(transactions);
                case "3" -> yearToDate(transactions);
                case "4" -> previousYear(transactions);
                case "5" -> searchByVendor(read, transactions);
                case "0" -> System.out.println("Returning to Ledger...");
                default -> System.out.println("Invalid Input");
            }
        }
    }
    private static void searchByVendor( Scanner read, ArrayList<Transaction> transactions) {
        System.out.println("=== Search by Vendor ===");

        System.out.print("Enter customer, sales platform, or supplier: ");
        String vendor = read.nextLine().trim();

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

        System.out.println("🌱 === Nursery Sales & Expenses — Month-to-Date ===");
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
        System.out.println("Start Date: " + startOfPreviousMonth);
        System.out.println("End Date: " + endOfPreviousMonth);

        for (Transaction transaction : transactions) {

            LocalDate transactionDate =
                    LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startOfPreviousMonth) && !transactionDate.isAfter(endOfPreviousMonth)) {

                System.out.println(transaction);
            }
        }
    }
    private static void yearToDate (ArrayList<Transaction> transactions) {

        LocalDate today = LocalDate.now();

        LocalDate startOfYear = today.withDayOfYear(1);

        System.out.println("=== Year to Date ===");
        System.out.println("Start Date: " + startOfYear);
        System.out.println("End Date: " + today);

        for (Transaction transaction : transactions) {

            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startOfYear) && !transactionDate.isAfter(today)) {
                System.out.println(transaction);
            }
        }
    }
    private static void previousYear (ArrayList<Transaction> transactions) {

        LocalDate today = LocalDate.now();

        LocalDate startOfPreviousYear = today.minusYears(1).withDayOfYear(1);

        LocalDate endOfPreviousYear = today.withDayOfYear(1).minusDays(1);

        System.out.println("=== Previous Year ===");
        System.out.println("Start Date: " + startOfPreviousYear);
        System.out.println("End Date: " + endOfPreviousYear);

        boolean found = false;

        for (Transaction transaction : transactions) {

            LocalDate transactionDate = LocalDate.parse(transaction.getDate());

            if (!transactionDate.isBefore(startOfPreviousYear) && !transactionDate.isAfter(endOfPreviousYear)) {

                System.out.println(transaction);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for the previous year.");
        }
    }
}

