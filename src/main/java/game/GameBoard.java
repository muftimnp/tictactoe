package game;

import state.StateMachine;

public class GameBoard {
  protected StateMachine stateMachine;

  public GameBoard() {
  }

  public void injectStateMachine(StateMachine state) {
    this.stateMachine = state;
  }

  public void draw() throws Exception {
    try {
      String[][] matrix = this.stateMachine.getState();
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          String entry = matrix[i][j];
          System.out.print(entry == null || entry.isEmpty() ? "?    " : entry + "    ");
        }
        System.out.println();
      }
    } catch (Exception e) {
      throw e;
    }
  }
}