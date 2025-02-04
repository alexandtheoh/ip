package alexis.exceptions;


/**
 * Represents an exception for the Todo class
 * A {@code ToDoException} is thrown when a todo class is created with an empty description string
 */
public class ToDoException extends Exception {
    public ToDoException() {
        super("OIIII your todo description cannot be empty");
    }
}
