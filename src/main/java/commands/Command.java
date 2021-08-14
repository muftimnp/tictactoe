package commands;

public class Command {
  private int xCoo;
  private int yCoo;
  private String mark;

  public Command(int x, int y, String mark) {
    this.xCoo = x;
    this.yCoo = y;
    this.mark = mark;
  }

  /**
   * @return the xCoo
   */
  public int getxCoo() {
    return xCoo;
  }

  /**
   * @param xCoo the xCoo to set
   */
  public void setxCoo(int xCoo) {
    this.xCoo = xCoo;
  }

  /**
   * @return the yCoo
   */
  public int getyCoo() {
    return yCoo;
  }

  /**
   * @param yCoo the yCoo to set
   */
  public void setyCoo(int yCoo) {
    this.yCoo = yCoo;
  }

  /**
   * @return the mark
   */
  public String getMark() {
    return mark;
  }

  /**
   * @param mark the mark to set
   */
  public void setMark(String mark) {
    this.mark = mark;
  }
}