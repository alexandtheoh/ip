package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

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
