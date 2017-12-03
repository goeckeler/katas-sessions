package spectator;

import java.io.IOException;

public class Main
{

  public static void main(String[] args)
    throws IOException
  {
    if (args.length < 2) {
      System.out.println("Arguments: [server hostname/ip] [server port]");
      System.exit(1);
    }

    SpectatorBoard spectatorBoard = null;
    String lookAndFeel = System.getProperty("lookAndFeel", "swing");
    switch (lookAndFeel) {
      case "fx" :
        spectatorBoard = new SpectatorFxBoard();
        break;
      default :
        spectatorBoard = new SpectatorSwingBoard();
        break;
    }
    final SpectatorBoard board = new SpectatorBoards(spectatorBoard);

    System.out.println("Starting spectator client ...");
    board.display(args);
  }
}
