package com.pluralsight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LedgerManager {
    public static void displayLedgerMenu(
            Scanner read,
            ArrayList<Transaction> transactions) {
        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {
            System.out.println("🌿 === Nursery Ledger ===");
            System.out.println("A) All Transactions");
            System.out.println("D) Plant Sales / Deposits");
            System.out.println("P) Nursery Expenses / Payments");
            System.out.println("R) Financial Reports");
            System.out.println("H) Return Home");

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
                case "R" -> ReportManager.displayReportsMenu(read, transactions);
                case "H" -> System.out.println("Return Home...");
                default -> System.out.println("Invalid Input");
            }
        }
    }
}
