package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {
    public static void addDeposit(
            Scanner read,
            ArrayList<Transaction> transactions) {

        System.out.println("🌿 === Record Plant Sale ===");

        System.out.print("Enter plant or sale description: ");
        String description = read.nextLine().trim();
        // description validation
        if(description.isEmpty() || description.contains("|")) {
            System.out.println("Invalid description");
            return;
        }

        System.out.print("Enter customer or sales platform: ");
        String vendor = read.nextLine().trim();
        // vendor validation
        if(vendor.isEmpty() || vendor.contains("|")) {
            System.out.println("Invalid input");
            return;
        }

        System.out.print("Enter sale amount: $");
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

        if (TransactionFileManager.saveTransaction(transaction)) {
            transactions.add(transaction);
            System.out.println("Deposit saved successfully!");
        }
    }
    public static void makePayment(
            Scanner read,
            ArrayList<Transaction> transactions) {

        System.out.println("🪴 === Record Nursery Expense ===");

        System.out.print("Enter expense description: ");
        String description = read.nextLine().trim();
        // description validation
        if(description.isEmpty() || description.contains("|")) {
            System.out.println("Invalid description");
            return;
        }

        System.out.print("Enter supplier or vendor: ");
        String vendor = read.nextLine().trim();
        // vendor validation
        if(vendor.isEmpty() || vendor.contains("|")) {
            System.out.println("Invalid vendor");
            return;
        }

        System.out.print("Enter expense amount: $");
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
            return;
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
        if (TransactionFileManager.saveTransaction(transaction)) {
            transactions.add(transaction);
            System.out.println("Payment saved successfully!");
        }
    }

}
