package se.lexicon;

import se.lexicon.exception.InsufficientBalanceException;

public class BankAccount {
    private int accountNumber;
    private String customerName;
    private double balance;

    public BankAccount(int accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void depositAmount(double depositAmount) {
        if(depositAmount < 0)
            throw new IllegalArgumentException("Illegal Argument Exception thrown... Enter a positive value to deposit...");
        balance += depositAmount;
        System.out.println("After depositing, current balance is:" + getBalance());
    }

    public void withdrawAmount(double withdrawAmount) throws InsufficientBalanceException {
        if(withdrawAmount < 0)
            throw new IllegalArgumentException("Illegal Argument Exception: Enter a positive value to withdraw...");
        if(withdrawAmount > balance)
            throw new InsufficientBalanceException("Insufficient Balance Exception: Enter a value less than current balance --> ", getBalance());
        balance -= withdrawAmount;
        System.out.println("After withdraw, current balance is:" + getBalance());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}