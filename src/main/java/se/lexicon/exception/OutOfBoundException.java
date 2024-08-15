package se.lexicon.exception;

public class OutOfBoundException extends Exception {
    private int enteredNumber;

    public OutOfBoundException(String message) {
        super(message);
    }

    public OutOfBoundException(String message, int enteredNumber) {
        super(message);
        this.enteredNumber = enteredNumber;
    }

    public int getEnteredNumber() {
        return enteredNumber;
    }
}
