package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class TransactionManager {

    public static void loadTransactions(ArrayList<Transaction> transactions) {

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
    public static boolean saveTransaction(Transaction transaction) {

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
}
