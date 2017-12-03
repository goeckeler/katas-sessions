package spectator;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jfree.chart.*;
import org.jfree.data.time.*;

public class SpectatorFxBoard
  extends Application
  implements SpectatorBoard
{
  private static final int INSET = 5;

  private TimeSeriesCollection trend;
  private ChartPanel trendPanel;
  private TitledPane highscorePanel;
  private TableView<Score> table;
  private TitledPane textPanel;
  private Label debugPanel;

  @Override
  public void display(String... arguments) {
    launch(arguments);
  }

  @Override
  public void updateRoundData(int roundNumber, String message) {
    Platform.runLater(() -> {
      textPanel.setText(String.format("Ablauf der Runde #%1$,d", roundNumber));
      debugPanel.setText(message);
    });
  }

  @Override
  public void showScores(int roundNumber, Scores scores) {
    Platform.runLater(() -> {
      highscorePanel.setText(String.format("Runde #%1$,d", roundNumber));
      ObservableList<Score> scoreList = FXCollections.observableList(scores.getScores());
      table.setItems(scoreList);
    });
  }

  @Override
  public void start(final Stage stage)
    throws Exception
  {
    final String host = getParameters().getUnnamed().get(0);
    final int port = Integer.parseInt(getParameters().getUnnamed().get(1));

    BorderPane layout = new BorderPane();

    highscorePanel = createHighscorePanel();
    TitledPane trendPanel = createTrendPanel();
    textPanel = createTextPanel();

    BorderPane.setAlignment(textPanel, Pos.TOP_LEFT);
    BorderPane.setMargin(textPanel, new Insets(INSET, INSET, INSET, INSET));
    BorderPane.setMargin(highscorePanel, new Insets(INSET, INSET, INSET, INSET));
    BorderPane.setMargin(trendPanel, new Insets(INSET, INSET, INSET, INSET));

    layout.setCenter(textPanel);
    layout.setRight(highscorePanel);
    layout.setBottom(trendPanel);

    Scene scene = new Scene(layout);

    stage.setScene(scene);
    stage.setTitle("Mäxchen!");
    stage.show();

    Executors.newSingleThreadExecutor().execute(() -> {
      try {
        Mediator mediator = new Mediator(host, port, this);
        mediator.connect();
      } catch (IOException ioex) {
        System.out.println("Cannot connect to server.");
        Platform.exit();
      }
    });
  }

  private TitledPane createTextPanel() {
    debugPanel = new Label("Das Spiel läuft");
    debugPanel.setFont(Fonts.TEXT_FONT);
    TitledPane panel = new TitledPane("Spielablauf", new VBox(debugPanel));
    panel.setFont(Fonts.HEADER_FONT);
    return panel;
  }

  @SuppressWarnings("unchecked")
  private TitledPane createHighscorePanel() {
    ObservableList<Score> emptyList = FXCollections.emptyObservableList();
    table = new TableView<>();
    table.setItems(emptyList);

    TableColumn<Score, String> playerColumn = new TableColumn<>("Spieler");
    playerColumn.setCellValueFactory(new PropertyValueFactory<>("player"));

    TableColumn<Score, Integer> scoreColumn = new TableColumn<>("Punkte");
    scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

    table.getColumns().setAll(playerColumn, scoreColumn);

    TitledPane panel = new TitledPane("Highscore", table);
    panel.setFont(Fonts.HEADER_FONT);
    return panel;
  }

  private TitledPane createTrendPanel() {
    // build chart panel
    trend = new TimeSeriesCollection();
    JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", "", trend, true, false, false);
    trendPanel = new ChartPanel(chart);

    SwingNode trend = new SwingNode();
    trend.setContent(trendPanel);

    TitledPane panel = new TitledPane("Punkte pro Minute", trend);
    panel.setFont(Fonts.HEADER_FONT);
    return panel;
  }

  @Override
  public void addDataPoint(long timestamp, String player, double pointsPerMinute) {
    TimeSeries series = this.trend.getSeries(player);
    if (series == null) {
      series = new TimeSeries(player, Second.class);
      this.trend.addSeries(series);
    }
    series.add(new Second(new Date(timestamp)), pointsPerMinute);
  }
}
