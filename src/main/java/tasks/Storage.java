package tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a the storage class for tasks list
 * A {@code Storage} interacts with the storage file, performs reads and writes
 */

public class Storage {
    private enum TaskTypeEnum {
        T, D, E
    }

    private String filePath;
    private TasksList tasksList;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads the save from filepath provided
     *
     * @return is a tasksList object derived from the save .txt file
     */
    public TasksList loadSave() {
        this.tasksList = new TasksList();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                try {
                    // parse string into task
                    String taskString = scanner.nextLine();

                    String[] taskStringArr = taskString.split("\\|");
                    if (TaskTypeEnum.T.name().equals(taskStringArr[0])) {
                        Task todo = new Todo(taskStringArr[1], taskStringArr[2]);
                        tasksList.addTask(todo);
                    } else if (TaskTypeEnum.D.name().equals(taskStringArr[0])) {
                        Task deadline = new Deadline(taskStringArr[1], taskStringArr[2], taskStringArr[3]);
                        tasksList.addTask(deadline);
                    } else if (TaskTypeEnum.E.name().equals(taskStringArr[0])) {
                        Task event = new Event(taskStringArr[1], taskStringArr[2], taskStringArr[3], taskStringArr[4]);
                        tasksList.addTask(event);
                    } else {
                        System.out.println("Error parsing save file.");
                    }
                } catch (Exception e) {
                    System.out.println("Error loading save data");
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e.getMessage());
        }

        return tasksList;
    }

    /**
     * saves the tasksList object into a .txt file at the file path provided
     */
    public void save() {
        writeFile(this.tasksList.toSaveString());
    }


    /**
     * writes the tasksList object into a .txt file at the file path provided
     */
    private void writeFile(String content) {
        try {
            // create directory
            String directoryPath = "data";
            // Create a File object for the directory
            File directory = new File(directoryPath);

            // Check if the directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a File object
            File file = new File(filePath);
            file.createNewFile();

            // Write to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
