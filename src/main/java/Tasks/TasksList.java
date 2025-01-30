package Tasks;

import AlexisExceptions.ToDoException;
import AlexisExceptions.InvalidPosException;
import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private List<Task> tasks;

    public TasksList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            tasks.remove(pos - 1);

            return task;
        } else {
            throw new InvalidPosException();
        }
    }

    public Task markTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            task.mark();
            return task;
        } else {
            throw new InvalidPosException();
        }

    }
    public Task unmarkTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            task.unmark();

            return task;
        } else {
            throw new InvalidPosException();
        }

    }

    public String toSaveString() {
        StringBuilder taskSave = new StringBuilder();
        for (Task task : this.tasks) {
            taskSave.append(task.toSaveString()).append("\n");
        }

        return taskSave.toString();
    }

    public String toString() {
        StringBuilder taskList = new StringBuilder();
        int counter = 1;

        for (Task task : this.tasks) {
            taskList.append(counter).append(".").append(task.toString()).append("\n");
            counter++;
        }

        return taskList.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }
}
