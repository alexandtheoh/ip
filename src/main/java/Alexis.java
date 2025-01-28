public class Alexis {
    private Storage storage;
    private TasksList tasksList;
    private Ui ui;

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

