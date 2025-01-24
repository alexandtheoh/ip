public class Deadline extends Task {
    protected String type = "[D]";
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDate() {
        return "(by: " + by + ")";
    }

    @Override
    public String toString() {
        return type + this.getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}
