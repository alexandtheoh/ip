package seedu.alexis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import alexis.exceptions.ToDoException;
import tasks.Todo;

public class TodoTest {

    @Test
    public void todoCreation() throws Exception {
        try {
            assertEquals("[T][ ] test", new Todo("0", "test").toString());
            assertEquals("[T][X] test", new Todo("1", "test").toString());
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void todoNoDescriptionExceptionThrown() throws Exception {
        try {
            Todo todo = new Todo("0", ""); // This should throw an exception
            fail("Expected ToDoException to be thrown"); // We should never reach here
        } catch (ToDoException e) {
            // This is the expected behavior
            assertEquals("OIIII your todo description cannot be empty", e.getMessage());
        }
    }
}

