package spectator;

public interface SpectatorBoard
  extends PointsPerMinuteListener
{
  public abstract void display(String... arguments);

  public abstract void updateRoundData(int roundNumber, String message);

  public abstract void showScores(int roundNumber, Scores scores);
}
