public class Event extends Task {
    protected String type = "[E]";
    protected String start;
    protected String end;
    public Event(String isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDate() {
        return "(from: " + start + "to: " + end + ")";
    }

    @Override
    public String toString() {
        return type + this.getStatusIcon() + " " + description + " (from:" + start + " to: " + end + ")";
    }

    @Override
    public String toSaveString() {
        return "T|" + this.getStatusString() + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}
