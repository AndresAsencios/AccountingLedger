package com.pluralsight;
import java.util.Scanner;


public class AccountingLedger {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("=== Accounting Ledger ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Enter your choice: ");
            choice = read.nextLine();
        }

    }
}
