package Tasks;

public class Deadline extends Task {
    protected String type = "[D]";

    protected TaskDate by;
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

    @Override
    public String toSaveString() {
        return "D|" + this.getStatusString() + "|" + this.description + "|" + this.by;
    }
}
