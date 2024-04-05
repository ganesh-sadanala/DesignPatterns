package Behavioral.Command;

import java.util.List;

// The Application class sets up object relations.
// It acts as a Sender: when something needs to be done, it
// creates a command object and executes it.
public class Application {
    private Command currentCommand;

    public void setCommand(Command command){
        currentCommand=command;
    }

    public void executeCommand(){
        if(currentCommand!=null) currentCommand.execute();
    }

    public void undoCommand(){
        if(currentCommand!=null) currentCommand.undo();
    }
}
