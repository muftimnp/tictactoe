package utils;

import exceptions.ValidationException;

import java.io.IOException;
import java.util.Scanner;

public class GameConsole {
  private Scanner sc;

  private GameConsole() {
    sc = new Scanner(System.in);
  }

  private static class GameConsoleHolder {
    private static final GameConsole OPEN_CONSOLE() {
      return new GameConsole();
    }
  }

  public static GameConsole openConsole() {
    return GameConsoleHolder.OPEN_CONSOLE();
  }

  public void closeConsole() {
    this.sc.close();
  }

  public void showMessage(String msg) {
    System.out.println(msg);
  }

  public int[] readCoordinates() throws IOException, ValidationException {
    try {
      String input = this.sc.nextLine();
      return Validator.validate(input);
    } catch (ValidationException nfe) {
      throw new ValidationException(nfe.getMessage());
    } catch (Exception ex) {
      throw ex;
    }
  }
}