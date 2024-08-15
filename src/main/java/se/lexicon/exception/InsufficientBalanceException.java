package se.lexicon.exception;

public class InsufficientBalanceException extends Exception {
    private double balance;

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, double balance) {
        super(message);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
