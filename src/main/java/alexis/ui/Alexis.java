package alexis.ui;

import tasks.Storage;
import tasks.TasksList;


/**
 * Represents the Alexis class
 * A {@code Alexis} is the main class for running the chatbot Alexis
 */
public class Alexis {
    private Storage storage;
    private TasksList tasksList;
    private Ui ui;


    /**
     * Create an Alexis object
     *
     * @param filePath is the location where the tasksList save is kept
     */
    public Alexis(String filePath) {
        storage = new Storage(filePath);
        tasksList = storage.loadSave();
        ui = new Ui(storage, tasksList);
    }

    public void run() {
        ui.start();
    }

    public static void main(String[] args) {
        new Alexis("data/alexis.txt").run();
    }
}

