package players;

import commands.Command;
import paths.IPath;
import paths.PathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CPU extends AbstractPlayer {
  protected List<Player> humanPlayers;
  private PathFinder pathFinder;

  public CPU() throws Exception {
    this(new ArrayList<>(), new PathFinder());
  }

  public CPU(List<Player> humanPlayers, PathFinder pathFinder) throws Exception {
    super();
    this.humanPlayers = humanPlayers;
    this.pathFinder = pathFinder;
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

  public void makeAMove() throws Exception {
    boolean res = tryWin() || tryBlock() || selectBestMove();
    System.out.println("CPU made a move? " + res);
  }

  public void injectHumanPlayer(Player player) {
    this.humanPlayers.add(player);
  }

  public boolean tryWin() {
    List<IPath> prospects = this.winningPaths.stream().filter(path -> (this.length - 1) == path.getCurrentSize())
        .collect(Collectors.toList());

    for (IPath path : prospects) {
      try {
        if (this.pathFinder.navigatePath(this, path)) {
          return true;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public boolean tryBlock() {
    for (Player player : this.humanPlayers) {
      List<IPath> humanProspects = player.winningPaths.stream()
          .filter(path -> (this.length - 1) == path.getCurrentSize()).collect(Collectors.toList());

      for (IPath path : humanProspects) {
        try {
          if (this.pathFinder.navigatePath(this, path)) {
            return true;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public boolean selectBestMove() throws Exception {
    for (int i = 0; i < this.length; i++) {
      for (int j = 0; j < this.length; j++) {
        if (null == this.stateMachine.getState()[i][j]) {
          Command command = new Command(i, j, this.getMark());
          this.execute(command);
          return true;
        }
      }
    }
    return false;
  }
}