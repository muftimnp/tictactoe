package state;

import commands.Command;
import exceptions.ValidationException;
import utils.Props;

public class Originator {
  protected String state[][];

  public Originator() throws Exception {
    int gridSize = Props.readGridSize();
    this.state = new String[gridSize][gridSize];
  }

  public void mutateState(Command command) throws Exception {
    try {
      if (this.state[command.getxCoo()][command.getyCoo()] != null) {
        throw new ValidationException("Place is already marked");
      }
      this.state[command.getxCoo()][command.getyCoo()] = command.getMark();
    } catch (Exception e) {
      throw e;
    }
  }

  public Memento save() {
    return new Memento(this.state);
  }

  public void restore(Memento m) {
    this.state = m.getState();
  }
}