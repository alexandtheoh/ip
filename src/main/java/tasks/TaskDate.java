package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a taskDate
 * A {@code TaskDate} can either contain a date as a date object or as a string
 */
public class TaskDate {
    private boolean isDate;
    private LocalDate date;
    private String dateString;

    /**
     * Create a TaskDate
     *
     * @param dateString is a string which will be parsed as a date, if the string is not parseable,
     *                   the date will be saved as a string instead of a date object
     */
    public TaskDate(String dateString) {
        try {
            date = LocalDate.parse(dateString);
            isDate = true;
        } catch (Exception e) {
            this.dateString = dateString;
            isDate = false;
        }
    }

    /**
     * Returns the date as a formatted string
     *
     * @return is a formatted date string
     */
    public String toString() {
        if (isDate) {
            return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.dateString;
        }
    }
}
