package Tasks;

public class Event extends Task {
    protected String type = "[E]";

    protected TaskDate start;
    protected TaskDate end;
    public Event(String isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = new TaskDate(start);
        this.end = new TaskDate(end);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDate() {
        return "(from: " + start.toString() + "to: " + end.toString() + ")";
    }

    @Override
    public String toString() {
        return type + this.getStatusIcon() + " " + description + " (from:" + start.toString() + " to: " + end.toString() + ")";
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return Formatted task with completion status.
     */
    @Override
    public String toSaveString() {
        return "E|" + this.getStatusString() + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}
