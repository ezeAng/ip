package src.main.c4po;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Deadline extends Task {

    protected String by;

    protected String byStr;
    protected String byTimeStr = "";
    protected LocalDate byDate;
    protected LocalTime byTime = LocalTime.now();
    public static String dateTimeParseErrMsg = "Could not parse date time format, please write yyyy-MM-dd [HHmm]. " +
            "Include 0s in the tenths place. Optionally include time, separated by a space";

    /**
     * Instantiates a instance of Deadline Task object with description and a deadline
     * which will be parsed into a DateTime format
     * @param description describes the task
     * @param by details the deadline in yyyy-mm-dd hhmm
     * @param isDone specifies whether the deadline task is done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        ArrayList<String> arr = new ArrayList<>(List.of(by.split(" ")));

        try {
            this.byDate = LocalDate.parse(arr.get(0));
            this.byStr = byDate.format(DateTimeFormatter.ofPattern("EEEE MMM d yyyy"));

            if (arr.size() > 1) {
                this.byTime = LocalTime.parse(arr.get(1), DateTimeFormatter.ofPattern("HHmm"));
                byTimeStr = byTime.format(DateTimeFormatter.ofPattern("HHmm"));
            }
        } catch (DateTimeParseException d) {
            Ui.print(dateTimeParseErrMsg);
        }
    }

    /**
     * Creates a new Deadline task with description and formatted-time input
     * @param description describes the task details
     * @param by a String which details the deadline date-time in yyyy-mm-dd hhmm format
     * @throws BotException if the datetime cannot be parsed
     */
    public Deadline(String description, String by) throws BotException {
        super(description, false);
        this.by = by;
        ArrayList<String> arr = new ArrayList<>(List.of(by.split(" ")));

        try {
            this.byDate = LocalDate.parse(arr.get(0));
            this.byStr = byDate.format(DateTimeFormatter.ofPattern("EEEE MMM d yyyy"));

            if (arr.size() > 1) {
                this.byTime = LocalTime.parse(arr.get(1), DateTimeFormatter.ofPattern("HHmm"));
                byTimeStr = byTime.format(DateTimeFormatter.ofPattern("HHmm"));
            }
        } catch (DateTimeParseException d) {
            throw new BotException(dateTimeParseErrMsg);

        }
    }



    @Override
    protected String getTaskFileFormat() {
        return "D" + " | " + super.getTaskFileFormat() + " | " + by;
    }


    /**
     * Returns an inline String that details the task type (Deadline) and the
     * task details
     * @return String
     */
    @Override
    public String getTaskInline() {
        return "[D]" + super.getTaskInline();
    }

    /**
     * Returns an inline String that details the index of the task
     * in the task list, the task type (Deadline) and the task details
     * @return String with index of task, deadline and description
     */
    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [D]" + super.getTaskInline() + " (by: " + byStr + " " + byTimeStr + ")";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + byStr + " " + byTimeStr + ")";
    }
}
