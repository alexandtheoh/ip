package seedu.Alexis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Tasks.Todo;
import AlexisExceptions.ToDoException;

public class TodoTest {

    @Test
    public void todo_creation() throws Exception {
        try {
            assertEquals("[T][ ] test", new Todo("0", "test").toString());
            assertEquals("[T][X] test", new Todo("1", "test").toString());
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void todo_no_description_exceptionThrown() throws Exception {
        try {
            Todo todo = new Todo("0", ""); // This should throw an exception
            fail("Expected ToDoException to be thrown"); // We should never reach here
        } catch (ToDoException e) {
            // This is the expected behavior
            assertEquals("OIIII your todo description cannot be empty", e.getMessage());
        }
    }
}

