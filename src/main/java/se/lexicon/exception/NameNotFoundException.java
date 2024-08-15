package se.lexicon.exception;

public class NameNotFoundException extends Exception {
    private String name;

    public NameNotFoundException(String message) {
        super(message);
    }

    public NameNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
