
import src.main.c4po.*;

public class C4PO {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    protected static final String FILE_P = "./data/tasks.txt";

    public String introduction;


    /**
     * for JavaFX Tutorial, temp
     */
    public C4PO() {
        ui = new Ui();
        parser = new Parser();
        this.storage = new Storage(C4PO.FILE_P);
        StringBuilder intro = new StringBuilder();
        try {
            tasks = new TaskList(storage.load());
            intro.append(Ui.showLoadedMsg(true)).append("\n");
            intro.append(tasks.printList(true, true));
        } catch (Exception e) {
            //Ui.print(e.getMessage());
            intro.append(Ui.showLoadingError(true));
            tasks = new TaskList();
        }
        this.introduction = intro.toString();
    }





    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws BotException {

        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage, true);
        } catch (Exception e) {
            throw new BotException(Ui.UNABLE_TO_UNDERSTAND_QUOTE
                    + "\n"
                    + Ui.showCommandError(true));
        }
    }
}
