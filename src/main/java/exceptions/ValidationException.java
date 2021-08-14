package exceptions;

public class ValidationException extends Exception {
  private String msg;

  public ValidationException(String msg) {
    super(msg);
    this.msg = msg;
  }

  @Override
  public String toString() {
    return msg;
  }
}