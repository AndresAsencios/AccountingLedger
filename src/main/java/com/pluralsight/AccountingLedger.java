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
        TransactionFileManager.loadTransactions(transactions);
        System.out.println("Loaded transactions: " + transactions.size());
        HomeMenu.displayHomeMenu(read, transactions);
    }
}
