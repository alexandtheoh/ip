package Tasks;

import AlexisExceptions.ToDoException;

public class Todo extends Task {
    protected String type = "[T]";
    public Todo(String isDone, String description) throws Exception {
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

    @Override
    public String toSaveString() {
        return "T|" + this.getStatusString() + "|" + this.description;
    }
}
