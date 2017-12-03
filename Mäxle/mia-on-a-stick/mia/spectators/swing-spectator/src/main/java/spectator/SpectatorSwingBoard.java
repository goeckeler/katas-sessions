package spectator;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;

import org.jfree.chart.*;
import org.jfree.data.time.*;

public class SpectatorSwingBoard
  implements SpectatorBoard
{
  private JFrame frmMxchen;
  private JTextPane roundText;
  private JScrollPane scrollPane;
  private JTable table;
  private JPanel leftColumn;
  private JProgressBar progressBar;
  private JPanel rightColumn;
  private JLabel scoresTableHeader;
  private JLabel roundHeader;
  private JLabel lblNchsteRunde;
  private JLabel lblHistorie;
  private ChartPanel chartPanel;
  private TimeSeriesCollection pointsPerMinute;
  private JSplitPane splitPane;

  private Mediator mediator;

  /**
   * Create the application.
   *
   * @throws IOException
   */
  public SpectatorSwingBoard()
    throws IOException
  {
    initialize();
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new IncrementProgressBar(progressBar, 1), 1, 1,
        TimeUnit.SECONDS);
  }

  /*
   * (non-Javadoc)
   * @see spectator.SpectatorBoard#show()
   */
  @Override
  public void display(String... arguments) {
    // GraphicsDevice defaultScreen =
    // GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    // if (defaultScreen.isFullScreenSupported()) {
    // frmMxchen.setUndecorated(true);
    // defaultScreen.setFullScreenWindow(frmMxchen);
    // }
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          frmMxchen.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    try {
      final String host = arguments[0];
      final int port = Integer.parseInt(arguments[1]);
      mediator = new Mediator(host, port, this);
      mediator.connect();
    } catch (IOException e) {
      System.out.println("Cannot connect to server.");
      e.printStackTrace();
    }
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmMxchen = new JFrame();
    frmMxchen.setTitle("Mäxchen!");
    frmMxchen.setSize(800, 600);
    frmMxchen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    rightColumn = new JPanel();
    rightColumn.setBackground(SystemColor.window);
    rightColumn.setBorder(null);
    rightColumn.setLayout(new MigLayout("", "[grow,fill]", "[15px][][][]"));
    scoresTableHeader = new JLabel("Warte auf ersten Punktestand...");
    scoresTableHeader.setFont(new Font("Arial", Font.BOLD, 18));
    rightColumn.add(scoresTableHeader, "cell 0 0,alignx left,aligny top");
    scoresTableHeader.setVerticalAlignment(SwingConstants.TOP);
    scoresTableHeader.setHorizontalAlignment(SwingConstants.LEFT);
    scoresTableHeader.setAlignmentY(Component.TOP_ALIGNMENT);

    table = new JTable();
    table.setOpaque(false);
    table.setBorder(null);
    table.setFillsViewportHeight(true);
    table.setFont(new Font("Arial", Font.PLAIN, 18));
    table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
    table.setRowHeight(25);
    table.setRowSelectionAllowed(false);

    scrollPane = new JScrollPane(table);
    scrollPane.setBorder(null);
    scrollPane.setOpaque(false);
    rightColumn.add(scrollPane, "cell 0 1");

    lblHistorie = new JLabel("Punkte pro Minute");
    lblHistorie.setFont(new Font("Arial", Font.BOLD, 18));
    rightColumn.add(lblHistorie, "cell 0 2");

    pointsPerMinute = new TimeSeriesCollection();
    JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", "", pointsPerMinute, true, false, false);
    chartPanel = new ChartPanel(chart);
    rightColumn.add(chartPanel, "cell 0 3");

    leftColumn = new JPanel();
    leftColumn.setBackground(Color.WHITE);
    leftColumn.setBorder(null);
    leftColumn.setLayout(new MigLayout("", "[][grow,fill]", "[][grow,fill][]"));

    roundHeader = new JLabel("Warte auf erste Runde...");
    roundHeader.setFont(new Font("Arial", Font.BOLD, 18));
    leftColumn.add(roundHeader, "cell 0 0 2 1");

    lblNchsteRunde = new JLabel("nächste Runde:");
    leftColumn.add(lblNchsteRunde, "cell 0 2");

    roundText = new JTextPane();
    roundText.setMargin(new Insets(0, 0, 0, 0));
    roundText.setOpaque(false);
    leftColumn.add(roundText, "cell 0 1 2 1,grow");
    roundText.setFont(new Font("Arial", Font.PLAIN, 18));
    roundText.setEditable(false);

    progressBar = new JProgressBar();
    progressBar.setValue(10);
    progressBar.setMaximum(10);
    leftColumn.add(progressBar, "cell 1 2,growx,aligny top");

    splitPane = new JSplitPane();
    splitPane.setResizeWeight(1.0);
    splitPane.setLeftComponent(leftColumn);
    splitPane.setRightComponent(rightColumn);
    frmMxchen.getContentPane().add(splitPane, BorderLayout.CENTER);
  }

  private TableModel createScoreModel(Scores scores) {
    return new ScoreTableModel(scores);
  }

  /*
   * (non-Javadoc)
   * @see spectator.SpectatorBoard#updateRoundData(int, java.lang.String)
   */
  @Override
  public void updateRoundData(final int roundNumber, final String message) {
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run() {
        roundHeader.setText("Ablauf von Runde " + roundNumber);
        roundText.setText(message);
        progressBar.setValue(0);
      }
    });
  }

  /*
   * (non-Javadoc)
   * @see spectator.SpectatorBoard#showScores(int, spectator.Scores)
   */
  @Override
  public void showScores(final int roundNumber, final Scores scores) {
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run() {
        scoresTableHeader.setText("Punktestand nach Runde " + roundNumber);
        table.setModel(createScoreModel(scores));
        table.invalidate();
      }
    });
  }

  private static class IncrementProgressBar
    implements Runnable
  {

    private final JProgressBar progressBar;
    private final int increment;

    public IncrementProgressBar(JProgressBar progressBar, int increment) {
      this.progressBar = progressBar;
      this.increment = increment;
    }

    @Override
    public void run() {
      progressBar.setValue(progressBar.getValue() + increment);
    }
  }

  @Override
  public void addDataPoint(long timestamp, String player, double pointsPerMinute) {
    TimeSeries series = this.pointsPerMinute.getSeries(player);
    if (series == null) {
      series = new TimeSeries(player, Second.class);
      this.pointsPerMinute.addSeries(series);
    }
    series.add(new Second(new Date(timestamp)), pointsPerMinute);
  }
}
