import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
public class Storage {
    private String filePath;
    private TasksList tasksList;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    // load
    public TasksList loadSave() {
        this.tasksList = new TasksList();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                try {
                    // parse string into task
                    String taskString = scanner.nextLine();

                    String[] taskStringArr = taskString.split("\\|");
                    if (AlexisSave.taskTypeEnum.T.name().equals(taskStringArr[0])) {
                        Task todo = new Todo(taskStringArr[1], taskStringArr[2]);
                        tasksList.addTask(todo);
                    } else if (AlexisSave.taskTypeEnum.D.name().equals(taskStringArr[0])) {
                        Task deadline = new Deadline(taskStringArr[1], taskStringArr[2], taskStringArr[3]);
                        tasksList.addTask(deadline);
                    } else if (AlexisSave.taskTypeEnum.E.name().equals(taskStringArr[0])) {
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
    // save
    public void save() {
        writeFile(this.tasksList.toSaveString());
    }

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
