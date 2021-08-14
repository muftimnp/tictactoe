package paths;

import commands.Command;

public class HorizontalPath extends IPath {
  private int localX = -1;

  public HorizontalPath(int x) {
    this.localX = x;
    initCommandsList();
  }

  @Override
  public boolean checkJoiningCondition(Command command) {
    int x = command.getxCoo();
    return this.localX == x;
  }

  public int getLocalX() {
    return this.localX;
  }
}