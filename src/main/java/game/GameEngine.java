package game;

import commands.Command;
import players.AbstractPlayer;
import players.Judge;
import players.Player;
import state.StateMachine;
import utils.Fields;
import utils.GameConsole;
import utils.Props;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
  private List<AbstractPlayer> players;
  private GameBoard gameBoard;
  private GameConsole gameConsole;
  private Judge judge;
  private StateMachine stateMachine;

  public GameEngine() throws Exception {
    this(new ArrayList<>());
  }

  public GameEngine(List<AbstractPlayer> players) throws Exception {
    this(new GameBoard(), GameConsole.openConsole(), new Judge(), StateMachine.getInstance(), players);
  }

  public GameEngine(GameBoard gameBoard, GameConsole gameConsole, Judge judge, StateMachine state,
      List<AbstractPlayer> players) throws Exception {
    this.gameBoard = gameBoard;
    this.players = players;
    this.gameConsole = gameConsole;
    this.judge = judge;
    this.stateMachine = state;
  }

  public void registerPlayers() throws Exception {
    try {
      Player player1 = new Player();
      player1.setName("P1");
      player1.setMark(Props.readProp(Fields.P1));
      player1.injectStateMachine(this.stateMachine);

      Player player2 = new Player();
      player2.setName("P2");
      player2.setMark(Props.readProp(Fields.P2));
      player2.injectStateMachine(this.stateMachine);

      players.add(player1);
      players.add(player2);
    } catch (Exception e) {
      throw e;
    }
  }

  public void startGame() {
    applyStateInjection();
    try {
      while (true) {
        for (AbstractPlayer player : players) {
          this.gameConsole.showMessage(player.getName() + ", Please enter your move:");

          while (true) {
            try {
              if (player instanceof Player) {
                int[] coo = this.gameConsole.readCoordinates();
                int x = coo[0], y = coo[1];
                Command command = new Command(x, y, player.getMark());
                player.execute(command);
              }
              this.gameBoard.draw();

              if (this.judge.locateCurrentPlayerMove(player)) {
                this.gameConsole.showMessage("Congratulations " + player.getName());
                return;
              }
              break;
            } catch (Exception e) {
              this.gameConsole.showMessage(e.getMessage());
              this.gameConsole.showMessage("Please try again.");
            }
          }
        }
      }
    } catch (Exception e) {
      throw e;
    } finally {
      this.gameConsole.closeConsole();
    }
  }

  private void applyStateInjection() {
    this.gameBoard.injectStateMachine(this.stateMachine);
  }
}