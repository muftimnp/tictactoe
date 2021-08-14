package paths;

import commands.Command;
import utils.Props;

public class MinorDiagonalPath extends IPath {
  public MinorDiagonalPath() {
    initCommandsList();
  }

  @Override
  public boolean checkJoiningCondition(Command command) throws Exception {
    try {
      int length = Props.readGridSize();
      int x = command.getxCoo(), y = command.getyCoo();

      for (int i = 0, j = length - 1; i < length; i++, j--) {
        if (x == i && y == j) {
          return true;
        }
      }
      return false;
    } catch (Exception e) {
      throw e;
    }
  }
}