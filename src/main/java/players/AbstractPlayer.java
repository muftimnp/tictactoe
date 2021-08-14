package players;

import commands.Command;
import paths.*;
import state.StateMachine;
import utils.Props;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
  protected List<IPath> winningPaths = new ArrayList<>();
  protected List<Command> commands = new ArrayList<>();

  protected StateMachine stateMachine;

  private String name;
  private String mark;

  protected int length;

  public abstract void execute(Command command) throws Exception;

  public AbstractPlayer() throws Exception {
    this.length = Props.readGridSize();

    for (int i = 0; i < length; i++) {
      this.winningPaths.add(new HorizontalPath(i));
      this.winningPaths.add(new VerticalPath(i));
    }
    this.winningPaths.add(new MajorDiagonalPath());
    this.winningPaths.add(new MinorDiagonalPath());
  }

  public boolean didIWin() throws Exception {
    try {
      boolean winnerPath = this.winningPaths.stream().filter(path -> this.length == path.getCurrentSize()).findFirst()
          .isPresent();
      return winnerPath;
    } catch (Exception e) {
      throw e;
    }
  }

  public void injectStateMachine(StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  public List<IPath> getWinningPaths() {
    return this.winningPaths;
  }

  /**
   * @return the command
   */
  public Command getLastCommand() {
    return this.commands.get(this.commands.size() - 1);
  }

  /**
   * @param command the command to set
   */
  public void addNewCommand(Command command) {
    this.commands.add(command);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the mark
   */
  public String getMark() {
    return mark;
  }

  /**
   * @param mark the mark to set
   */
  public void setMark(String mark) {
    this.mark = mark;
  }
}