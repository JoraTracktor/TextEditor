package commands;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class ControllerCommands {

    //private Stack<Command> history = new Stack<>();
    private Command command;
    private ExecutorService executorService;

    public ControllerCommands() {
        executorService = newCachedThreadPool();
    }

    public void executeCommand(){
        executorService.submit(() -> command.execute() );//? true : history.push(command));
        //command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

   /* private void push(Command command) {
        history.push(command);
    }*/

    /*private Command pop() {
        return history.pop();
    }*/
}
