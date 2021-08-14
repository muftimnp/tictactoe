package paths;

import commands.Command;
import players.AbstractPlayer;

public class PathFinder {

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
}