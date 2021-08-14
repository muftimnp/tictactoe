package players;

import paths.PathFinder;

public class Judge {
  private PathFinder pathFinder;

  public Judge() throws Exception {
    this(new PathFinder());
  }

  public Judge(PathFinder pFinder) {
    this.pathFinder = pFinder;
  }

  public boolean locateCurrentPlayerMove(AbstractPlayer player) throws Exception {
    try {
      this.pathFinder.locatePlayerMove(player);
      return this.isWinner(player);
    } catch (Exception e) {
      e.printStackTrace(System.out);
      throw e;
    }
  }

  public boolean isWinner(AbstractPlayer player) throws Exception {
    try {
      return player.didIWin();
    } catch (Exception e) {
      throw e;
    }
  }
}