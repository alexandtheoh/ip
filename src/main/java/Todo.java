public class Todo extends Task {
    protected String type = "[T]";
    public Todo(String description) throws Exception {
        super(description);
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
}
