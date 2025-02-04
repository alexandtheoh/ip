package tasks;

import alexis.exceptions.ToDoException;

/**
 * Represents a todo task.
 */

public class Todo extends Task {
    protected String type = "[T]";

    /**
     * Constructs a new Todo task, throws a ToDoException if description is empty
     *
     * @param description Description of the task.
     * @param isDone describes if the task is done.
     */
    public Todo(String isDone, String description) throws ToDoException {
        super(isDone, description);
        if ("".equals(description)) {
            throw new ToDoException();
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + this.getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the Todo.
     *
     * @return Formatted task with completion status.
     */
    @Override
    public String toSaveString() {
        return "T|" + this.getStatusString() + "|" + this.description;
    }
}
