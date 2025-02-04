package alexis.exceptions;

/**
 * Represents an exception for the UI
 * A {@code InvalidPosExeption} is thrown when an invaid position is passed to the TasksList class
 */
public class InvalidPosException extends Exception {
    public InvalidPosException() {
        super("Invalid Position for Tasks.Task List.");
    }
}
