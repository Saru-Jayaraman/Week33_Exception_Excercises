package se.lexicon.exception;

public class DuplicateNameException extends Exception {
    private String name;

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, String name) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
