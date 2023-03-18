
/**
 * Enum responsible for searching for errors
 */
public enum ERROR {
    INVALID_CHARACTER("There is no such champion in the database named: "),
    INVALID_ITEM("There is no such item in the database named: "),
    UNKNOWN_COMMAND("Unknown command");

    private final String message;

    ERROR(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}