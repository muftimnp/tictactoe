package paths;

import commands.Command;
import players.AbstractPlayer;
import players.CPU;
import state.StateMachine;
import utils.Props;

import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {

  private StateMachine stateMachine;

  public PathFinder() throws Exception {
    this(StateMachine.getInstance());
  }

  public PathFinder(StateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  public void locatePlayerMove(AbstractPlayer player) throws Exception {
    try {
      Command command = player.getLastCommand();
      player.getWinningPaths().stream().forEach(path -> {
        try {
          path.handleCommand(command);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } catch (Exception e) {
      throw e;
    }
  }

  public boolean navigatePath(CPU cpu, IPath path) throws Exception {
    int length = Props.readGridSize();
    if (path instanceof HorizontalPath) {
      int x = ((HorizontalPath) path).getLocalX();
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < length; i++) {
        final int target = i;
        boolean alreadyUsed = usedCoo.stream().filter(coo -> target == coo[1]).findAny().isPresent();
        if (!alreadyUsed && null == this.stateMachine.getState()[x][target]) {
          Command cmd = new Command(x, target, cpu.getMark());
          cpu.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof VerticalPath) {
      int y = ((VerticalPath) path).getLocalY();
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < length; i++) {
        final int target = i;
        boolean alreadyUsed = usedCoo.stream().filter(coo -> target == coo[0]).findAny().isPresent();
        if (!alreadyUsed && null == this.stateMachine.getState()[target][y]) {
          Command cmd = new Command(target, y, cpu.getMark());
          cpu.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof MajorDiagonalPath) {
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0; i < length; i++) {
        final int target = i;
        boolean alreadyUsed = usedCoo.stream().filter(coo -> target == coo[0] && target == coo[1]).findAny()
            .isPresent();
        if (!alreadyUsed && null == this.stateMachine.getState()[target][target]) {
          Command cmd = new Command(target, target, cpu.getMark());
          cpu.execute(cmd);
          return true;
        }
      }
    }

    else if (path instanceof MinorDiagonalPath) {
      List<int[]> usedCoo = path.getCommands().stream()
          .map(command -> new int[] { command.getxCoo(), command.getyCoo() }).collect(Collectors.toList());
      for (int i = 0, j = length - 1; i < length; i++, j--) {
        final int targetX = i;
        final int targetY = j;
        boolean alreadyUsed = usedCoo.stream().filter(coo -> targetX == coo[0] && targetY == coo[1]).findAny()
            .isPresent();
        if (!alreadyUsed && null == this.stateMachine.getState()[targetX][targetY]) {
          Command cmd = new Command(targetX, targetY, cpu.getMark());
          cpu.execute(cmd);
          return true;
        }
      }
    }
    return false;
  }
}