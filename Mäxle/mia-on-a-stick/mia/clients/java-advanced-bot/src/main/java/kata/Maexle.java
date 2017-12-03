package kata;

import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Maexle
{
  public static int port = 9000;
  public static InetAddress host;
  public static int packageSize = 1024;
  public static String team = "beat-me";

  static int[] dicesScore = {
    31, 32, 41, 42, 43, 51, 52, 53, 54, 61, 62, 63, 64, 65, 11, 22, 33, 44, 55, 66, 21
  };
  static Map<Integer, Integer> dices = new HashMap<>();

  static {
    for (int index = 0; index < dicesScore.length; ++index) {
      dices.put(dicesScore[index], index);
    }
  };

  DatagramSocket socket;
  byte[] sendData = new byte[packageSize];

  private String command = "";
  private int lastDice = 31;

  public static void main(String args[])
    throws Exception
  {
    // host = InetAddress.getByName("127.0.0.1");
    host = InetAddress.getByName("iteralean.iteratec.de");

    Maexle maexle = new Maexle();
    maexle.run();
  }

  private void run()
    throws Exception
  {
    socket = new DatagramSocket();
    send("REGISTER;" + team);
    if (!receive().startsWith("REGISTERED")) {
      System.err.println("Start again with different name.");
      System.exit(1);
    };

    while (true) {
      command = receive();

      if (is("ROUND STARTING")) {
        lastDice = 31;
        String token = command.split(";")[1];
        send("JOIN;" + token);
      } else if (is("ROUND STARTED")) {} else if (is("YOUR TURN")) {
        String token = command.split(";")[1];
        if (rank(lastDice) == -1 || rank(lastDice) >= dicesScore.length - 4) {
          send("SEE;" + token);
        } else {
          send("ROLL;" + token);
        }
      } else if (is("ROLLED")) {
        String token = command.split(";")[2];
        int dice = getRoll(command, 1);

        if (rank(dice) <= rank(lastDice)) {
          int left = dicesScore.length - 1 - rank(lastDice);
          int offset = 1 + (left > 2 ? 1 : 0);
          send("ANNOUNCE;" + asDice(dicesScore[rank(lastDice) + offset]) + ";" + token);
        } else {
          send("ANNOUNCE;" + asDice(dice) + ";" + token);
        }
      } else if (is("PLAYER LOST")) {
        System.out.println("--------------------------------------------");
        System.out.println(command);
        System.out.println("============================================");
      } else if (is("ANNOUNCED")) {
        lastDice = getRoll(command, 2);
      }
    }
  }

  private int rank(int dice) {
    Integer rank = dices.get(dice);
    if (rank == null) return -1;
    return rank;
  }

  private String asDice(int dice) {
    return "" + dice / 10 + "," + dice % 10;
  }

  public int getRoll(String command, int index) {
    String dice = command.split(";")[index];
    String[] nums = dice.split(",");
    return Integer.valueOf(nums[0]) * 10 + Integer.valueOf(nums[1]);
  }

  public void send(String command)
    throws Exception
  {
    byte[] data = command.getBytes();
    DatagramPacket message = new DatagramPacket(data, data.length, host, port);
    System.out.println("SEND: \"" + command + "\"");
    socket.send(message);
  }

  public String receive()
    throws Exception
  {
    byte[] receiveData = new byte[1024];

    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    socket.receive(receivePacket);

    String data = new String(receivePacket.getData()).trim();
    System.out.println("GOT : \"" + data + "\"");
    return data;
  }

  private boolean is(String command) {
    return this.command.startsWith(command);
  }
}
