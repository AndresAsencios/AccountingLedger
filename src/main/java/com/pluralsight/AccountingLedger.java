package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;


public class AccountingLedger {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
        TransactionFileManager.loadTransactions(transactions);
        System.out.println("Loaded transactions: " + transactions.size());
        HomeMenu.displayHomeMenu(read, transactions);
    }
}
