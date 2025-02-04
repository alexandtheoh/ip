package tasks;

/**
 * Represents a deadline
 * A {@code Deadline} extends Task class and contains an do by date
 */
public class Deadline extends Task {
    private String type = "[D]";
    private TaskDate by;


    /**
     * Creates a Deadline
     */
    public Deadline(String isDone, String description, String by) {
        super(isDone, description);
        this.by = new TaskDate(by);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDate() {
        return "(by: " + by.toString() + ")";
    }

    @Override
    public String toString() {
        return type + this.getStatusIcon() + " " + description + " (by: " + by.toString() + ")";
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * @return Formatted task with completion status.
     */
    @Override
    public String toSaveString() {
        return "D|" + this.getStatusString() + "|" + this.description + "|" + this.by;
    }
}
