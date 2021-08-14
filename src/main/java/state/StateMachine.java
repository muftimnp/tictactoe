package state;

import commands.Command;

public class StateMachine {
  private Caretaker caretaker;
  private Originator originator;

  private StateMachine() throws Exception {
    this(new Caretaker(), new Originator());
  }

  protected StateMachine(Caretaker caretaker, Originator originator) throws Exception {
    this.originator = originator;
    this.caretaker = caretaker;
  }

  private static class StateMachineHolder {
    private static final StateMachine INSTANCE() throws Exception {
      return new StateMachine();
    };
  }

  public static StateMachine getInstance() throws Exception {
    try {
      return StateMachineHolder.INSTANCE();
    } catch (Exception e) {
      throw e;
    }
  }

  public void mutateState(Command command) throws Exception {
    try {
      this.originator.mutateState(command);
      this.caretaker.addMemento(originator.save());
    } catch (Exception e) {
      throw e;
    }
  }

  public String[][] getState() {
    return this.caretaker.getMemento().getState();
  }
}