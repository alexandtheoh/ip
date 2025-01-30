package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskDate {
    private boolean isDate;
    private LocalDate date;
    private String dateString;

    public TaskDate(String dateString) {
        try {
            date = LocalDate.parse(dateString);
            isDate = true;
        } catch (Exception e) {
            this.dateString = dateString;
            isDate = false;
        }
    }

    public String toString() {
        if (isDate) {
            return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.dateString;
        }
    }
}
