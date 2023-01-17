import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    protected static ArrayList<Task> taskList = new ArrayList<>();

    protected static Integer listSize = 0; //or just increment accordingly

    public Integer index;

    //Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.index = listSize + 1;
        //add to taskList
        taskList.add(this);
        listSize++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static String mark(Integer index, String markOption) {
        if (index > taskList.size()) {
            return "No such item exists in list";
        }
        Task task = taskList.get(index - 1);
        if (markOption.equalsIgnoreCase("mark")) {
            task.markDone();
        } else if (markOption.equalsIgnoreCase("unmark")){
            task.markUndone();
        }
        return task.getTaskInline();
    }

    //Mark as done
    public void markDone() {

        this.isDone = true;
    }

    //Mark undone
    public void markUndone() {
        this.isDone = false;
    }

    //Get inline print of task description with specified index
    public String getTaskInline(Integer index) {
        String statusIcon = this.getStatusIcon();
        return index.toString() + ". " + "[" + statusIcon + "] " + this.description;
    }

    public String getTaskInline() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public static void printList() {
        System.out.println("Master, here are the items in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Integer val = i+1;
            String listInline = taskList.get(i).getTaskInline(val);

            System.out.println(listInline);
        }
    }

}
