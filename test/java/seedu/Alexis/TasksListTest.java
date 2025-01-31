package seedu.Alexis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Tasks.TasksList;
import Tasks.Todo;
import AlexisExceptions.InvalidPosException;

public class TasksListTest {

    @Test
    public void tasksList_creation() throws Exception {
        try {
            TasksList tasksList = new TasksList();
            tasksList.addTask(new Todo("0", "test1"));
            tasksList.addTask(new Todo("1", "test2"));
            assertEquals("1.[T][ ] test1\n2.[T][X] test2\n", tasksList.toString());
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void tasksList_pos_exceptionThrown() throws Exception {
        try {
            TasksList tasksList = new TasksList();
            tasksList.addTask(new Todo("0", "test1"));
            tasksList.addTask(new Todo("1", "test2"));
            tasksList.deleteTask(3);
            fail("Expected InvalidPosException to be thrown"); // We should never reach here
        } catch (InvalidPosException e) {
            assertEquals("Invalid Position for Tasks.Task List.", e.getMessage());
        }
    }
}