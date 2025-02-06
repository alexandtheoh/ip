package seedu.alexis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import alexis.exceptions.InvalidPosException;
import tasks.TasksList;
import tasks.Todo;


public class TasksListTest {

    @Test
    public void tasksListCreation() throws Exception {
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
    public void tasksListPosExceptionThrown() throws Exception {
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
