package spectator;

import java.io.IOException;

import udphelper.UdpCommunicator;

public class Mediator
{
  private final UdpCommunicator communicator;
  private boolean connected = false;

  public Mediator(String serverHost, int serverPort, SpectatorBoard board)
    throws IOException
  {
    RoundListener roundListener = new ThrottlingRoundListener(new RoundListener()
    {
      @Override
      public void roundCompleted(int roundNumber, String completeRound) {
        board.updateRoundData(roundNumber, completeRound);
      }
    });

    ScoreListener highscoreUpdater = new ScoreListener()
    {
      @Override
      public void scoresAfterRound(Scores scores, int roundNumber) {
        board.showScores(roundNumber, scores);
      }
    };
    ScoreListener pointsPerMinuteUpdater = new PointsPerMinuteCalculator(board);

    DataCollector dataCollector = new DataCollector(roundListener);
    dataCollector.addScoreListener(highscoreUpdater);
    dataCollector.addScoreListener(pointsPerMinuteUpdater);

    communicator = new UdpCommunicator(serverHost, serverPort);
    communicator.addMessageListener(dataCollector);
    communicator.getMessageSender().send("REGISTER_SPECTATOR;spectator");
  }

  public synchronized void connect()
    throws IOException
  {
    if (!connected) {
      communicator.listenForMessages();
      connected = true;
    }
  }
}
