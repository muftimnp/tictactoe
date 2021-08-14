package paths;

import commands.Command;

public class MajorDiagonalPath extends IPath {

  public MajorDiagonalPath() {
    initCommandsList();
  }

  @Override
  public boolean checkJoiningCondition(Command command) throws Exception {
    try {
      int x = command.getxCoo(), y = command.getyCoo();
      return x == y;
    } catch (Exception e) {
      throw e;
    }
  }
}