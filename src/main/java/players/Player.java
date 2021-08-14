package players;

import commands.Command;

public class Player extends AbstractPlayer {

  public Player() throws Exception {
    super();
  }

  @Override
  public void execute(Command command) throws Exception {
    try {
      this.addNewCommand(command);
      this.stateMachine.mutateState(command);
    } catch (Exception e) {
      throw e;
    }
  }
}