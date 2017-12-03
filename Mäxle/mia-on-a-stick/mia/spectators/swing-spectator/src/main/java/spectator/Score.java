package spectator;

import javafx.beans.property.*;

public class Score
  implements Comparable<Score>
{
  private final StringProperty player = new SimpleStringProperty(this, "player");
  private final IntegerProperty score = new SimpleIntegerProperty(this, "score");

  public Score(final String player, final Integer score) {
    setPlayer(player);
    setScore(score);
  }

  public void setPlayer(final String player) {
    this.player.set(player);
  }

  public String getPlayer() {
    return player.get();
  }

  public void setScore(Integer value) {
    score.set(value);
  }

  public Integer getScore() {
    return score.get();
  }

  @Override
  public int compareTo(final Score that) {
    int compare = that.getScore() - this.getScore();
    if (compare == 0) {
      compare = this.getPlayer().compareToIgnoreCase(that.getPlayer());
    }
    return compare;
  }
}
