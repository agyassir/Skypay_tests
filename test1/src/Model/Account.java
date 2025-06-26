package Model;

import Exceptions.EmptyTransactionException;
import Exceptions.InvalidTransactionAmountException;
import Service.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {

    private int balance=0;

    private Client client;
    private List<Transaction> transactions = new ArrayList<>();
    private  DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Account(int balance, Client client, List<Transaction> transactions, DateTimeFormatter DATE_FORMATTER) {

        this.balance = balance;
        this.client = client;
        this.transactions = transactions;
        this.DATE_FORMATTER = DATE_FORMATTER;
    }

    public Account() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public DateTimeFormatter getDATE_FORMATTER() {
        return DATE_FORMATTER;
    }

    public void setDATE_FORMATTER(DateTimeFormatter DATE_FORMATTER) {
        this.DATE_FORMATTER = DATE_FORMATTER;
    }

    @Override
    public void deposit(int amount) {
        this.balance += amount;
        this.transactions.add(new Transaction(LocalDate.now(), amount, this.balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount>this.balance){
            throw new InvalidTransactionAmountException("you don't have enough balance");
        }
        this.balance-=amount;
        this.transactions.add(new Transaction(LocalDate.now(), -amount, this.balance));
    }

    @Override
    public void printStatement() {


        if (this.transactions.isEmpty()){
            throw new EmptyTransactionException("you don't have a transaction");
        }

        System.out.println("Date       || Amount || Balance");

        // Create a reversed view of the list for printing without modifying the original.
        List<Transaction> reversedTransactions = new ArrayList<>(this.transactions);
        Collections.reverse(reversedTransactions);

        for (Transaction transaction : reversedTransactions) {
            // Format and print each line of the statement.
            // Note: The document says not to worry about spacing[cite: 11], but printf helps with alignment.
            System.out.printf("%-10s || %-6d || %d%n",
                    transaction.getDate().format(DATE_FORMATTER),
                    transaction.getAmount(),
                    transaction.getBalance());
        }
    }
}
