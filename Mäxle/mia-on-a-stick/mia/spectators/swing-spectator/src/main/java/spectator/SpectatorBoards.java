package spectator;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite board that delegates to all registered spectator boards.
 */
public class SpectatorBoards
  implements SpectatorBoard
{
  private final List<SpectatorBoard> boards;

  public SpectatorBoards(SpectatorBoard... boards) {
    this.boards = new ArrayList<>(Math.max(1, boards.length));
    for (SpectatorBoard board : boards) {
      System.out.println("Registering board \"" + board.getClass().getSimpleName() + "\".");
      this.boards.add(board);
    }
  }

  @Override
  public void display(String... arguments) {
    boards.forEach(board -> {
      System.out.println("Launching board \"" + board.getClass().getSimpleName() + "\".");
      board.display(arguments);
    });
  }

  @Override
  public void updateRoundData(final int roundNumber, final String message) {
    System.out.println(String.format("Round #%1$,d - %2$s", roundNumber, message));
    boards.forEach(board -> board.updateRoundData(roundNumber, message));
  }

  @Override
  public void showScores(final int roundNumber, final Scores scores) {
    System.out.println(String.format("Round #%1$,d - %2$s", roundNumber, scores));
    boards.forEach(board -> board.showScores(roundNumber, scores));
  }

  @Override
  public void addDataPoint(final long timestamp, final String player, final double pointsPerMinute) {
    System.out.println(String.format("Trend report states %1$s scored %2$,.0f points in the last minute", player,
        pointsPerMinute));
    boards.forEach(board -> board.addDataPoint(timestamp, player, pointsPerMinute));
  }
}
