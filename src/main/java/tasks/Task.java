package tasks;

/**
 * Represents a task.
 * A {@code Task} can be marked as completed or not and has a decription.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task
     *
     * @param isDone "0" represents an undone task anything else means the task is complete
     * @param description is a string description of the task
     */
    public Task(String isDone, String description) {
        this.description = description;
        if (isDone.equals("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getStatusString() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }

    public String getDate() {
        return "";
    }

    public String getType() {
        return "";
    }

    public String toString() {
        return "";
    }

    public String toSaveString() {
        return "";
    }
}
