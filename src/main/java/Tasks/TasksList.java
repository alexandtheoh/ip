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

    /**
     * Adds Task to task list
     *
     * @param task is the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list
     *
     * @param is the position of the task to be deleted, InvalidPosException is thrown if pos is out of bounds
     *
     * @return is the task which was deleted
     */
    public Task deleteTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            tasks.remove(pos - 1);

            return task;
        } else {
            throw new InvalidPosException();
        }
    }

    /**
     * Marks a task from the list
     *
     * @param is the position of the task to be marked, InvalidPosException is thrown if pos is out of bounds
     *
     * @return is the task which was marked
     */
    public Task markTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            task.mark();
            return task;
        } else {
            throw new InvalidPosException();
        }

    }

    /**
     * Unmarks a task from the list
     *
     * @param is the position of the task to be unmarked, InvalidPosException is thrown if pos is out of bounds
     *
     * @return is the task which was unmarked
     */
    public Task unmarkTask(int pos) throws InvalidPosException {
        if (pos <= this.tasks.size() && pos > 0) {
            Task task = this.tasks.get(pos - 1);
            task.unmark();

            return task;
        } else {
            throw new InvalidPosException();
        }

    }

    /**
     * Returns a string representation of the TaskList to be used for the save file.
     *
     * @return Formatted task with completion status and date.
     */
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

    /**
     * Returns all the tasks which match the search string
     *
     * @return task list matching search string
     */
    public String search(String searchString) {
        StringBuilder searchList = new StringBuilder();
        int counter = 1;

        for (Task task : this.tasks) {
            if (task.getDescription().contains(searchString)) {
                searchList.append(counter).append(".").append(task.toString()).append("\n");
                counter++;
            }
        }

        return searchList.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }
}
