package tictactoe;

public class Game
{
  private static final int SIZE = 3;

  private int[][] board = new int[SIZE][SIZE];

  public Player winner() {
    return Player.NONE;
  }
}
