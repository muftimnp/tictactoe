package paths;

import commands.Command;

import java.util.ArrayList;
import java.util.List;

public abstract class IPath {
  private List<Command> commands;

  public abstract boolean checkJoiningCondition(Command command) throws Exception;

  public void handleCommand(Command command) throws Exception {
    try {
      if (checkJoiningCondition(command)) {
        this.commands.add(command);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  public void initCommandsList() {
    this.commands = new ArrayList<>();
  }

  public int getCurrentSize() {
    return this.commands.size();
  }

  public List<Command> getCommands() {
    return this.commands;
  }
}