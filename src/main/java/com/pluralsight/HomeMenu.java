package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeMenu {
    public static void displayHomeMenu(Scanner read, ArrayList<Transaction> transactions) {
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
                case "D" -> TransactionManager.addDeposit(read, transactions);
                case "P" -> TransactionManager.makePayment(read, transactions);
                case "L" -> LedgerManager.displayLedgerMenu(read, transactions);
                case "X" -> System.out.println("Close, Goodbye!");
                default -> System.out.println("Invalid Input");
            }
        }
    }
}
