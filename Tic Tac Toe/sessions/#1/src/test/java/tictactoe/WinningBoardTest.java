package tictactoe;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WinningBoardTest
{
  private Game game = new Game();
  
  @Test
  public void emptyBoardShouldHaveNoWinner() {
    assertThat(game.winner(), equalTo(Player.NONE));
  }
}
