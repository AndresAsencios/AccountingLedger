package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeMenu {
    public static void displayHomeMenu(Scanner read, ArrayList<Transaction> transactions) {
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║      🌱 THE GREEN LEDGER         ║");
            System.out.println("║     Plant Nursery Accounting     ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.println();
            System.out.println("🌿 D) Record Plant Sale / Deposit");
            System.out.println("🪴 P) Record Nursery Expense");
            System.out.println("📖 L) View Nursery Ledger");
            System.out.println("🚪 X) Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            choice = read.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "D" -> TransactionManager.addDeposit(read, transactions);
                case "P" -> TransactionManager.makePayment(read, transactions);
                case "L" -> LedgerManager.displayLedgerMenu(read, transactions);
                case "X" -> System.out.println("🌱 Thank you for using The Green Ledger!");
                default -> System.out.println("Invalid Input");
            }
        }
    }
}
